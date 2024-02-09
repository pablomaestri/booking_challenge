package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.BookingDTO;
import com.psm.bookingchallenge.dtos.requests.ChangeBookingStatusDTO;
import com.psm.bookingchallenge.dtos.requests.RebookBookingDTO;
import com.psm.bookingchallenge.dtos.requests.SaveBookingDTO;
import com.psm.bookingchallenge.factories.dtos.BookingDTOFactory;
import com.psm.bookingchallenge.factories.models.BookingFactory;
import com.psm.bookingchallenge.models.*;
import com.psm.bookingchallenge.models.enums.BookingStatusEnum;
import com.psm.bookingchallenge.models.enums.RoleEnum;
import com.psm.bookingchallenge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingStatusRepository bookingStatusRepository;
    @Autowired
    private PropertyDateBlockRepository propertyDateBlockRepository;
    @Autowired
    private BookingFactory bookingFactory;
    @Autowired
    private BookingDTOFactory bookingDTOFactory;


    @Override
    public BookingDTO getBooking(Long bookingId) {
        Optional<Booking> optBooking = bookingRepository.findById(bookingId);
        return optBooking.map(booking -> bookingDTOFactory.create(booking)).orElse(null);
    }

    @Override
    public BookingDTO saveBooking(SaveBookingDTO saveBookingDTO) {

        Optional<Property> optProperty = propertyRepository.findById(saveBookingDTO.getPropertyId());
        Optional<User> optGuest = userRepository.findById(saveBookingDTO.getGuestId());
        Optional<BookingStatus> optBStatus = bookingStatusRepository.findByNameIgnoreCase(BookingStatusEnum.REQUESTED.name());

        if (optProperty.isEmpty() || optGuest.isEmpty() || optBStatus.isEmpty()) {
            return null;
        }

        Booking booking = bookingFactory.create(saveBookingDTO.getId(), optProperty.get(), optGuest.get(),
                saveBookingDTO.getCheckin(), saveBookingDTO.getCheckout(), optBStatus.get(),
                saveBookingDTO.getPeopleAmount(), saveBookingDTO.getBabysAmount(), saveBookingDTO.getFoodPreferences(),
                saveBookingDTO.getHasPets());

        //if it's editing maintains the status
        if (saveBookingDTO.getId()!=null) {
            Optional<Booking> optBooking = bookingRepository.findById(saveBookingDTO.getId());
            booking.setStatus(optBooking.get().getStatus());
        }

        booking = bookingRepository.save(booking);

        return bookingDTOFactory.create(booking);
    }

    @Override
    public BookingDTO changeBookingStatus(ChangeBookingStatusDTO changeBookingStatusDTO) {
        Optional<Booking> optBooking = bookingRepository.findById(changeBookingStatusDTO.getBookingId());
        Optional<BookingStatus> optBookingStatus = bookingStatusRepository.findById(changeBookingStatusDTO
                .getBookingStatusId());

        if (optBooking.isEmpty() || optBookingStatus.isEmpty()) {
            return null;
        }
        optBooking.get().setStatus(optBookingStatus.get());
        bookingRepository.save(optBooking.get());
        return bookingDTOFactory.create(optBooking.get());
    }

    @Override
    public BookingDTO rebookBooking(RebookBookingDTO rebookBookingDTO) {
        Optional<Booking> optBooking = bookingRepository.findById(rebookBookingDTO.getBookingId());
        Optional<BookingStatus> optBookingStatus = bookingStatusRepository.findByNameIgnoreCase(BookingStatusEnum.REQUESTED.name());
        if (optBooking.isEmpty() || optBookingStatus.isEmpty()) {
            return null;
        }
        Booking bookingToSave = optBooking.get();
        bookingToSave.setStatus(optBookingStatus.get());
        bookingToSave.setCheckin(rebookBookingDTO.getCheckin());
        bookingToSave.setCheckout(rebookBookingDTO.getCheckout());
        bookingRepository.save(optBooking.get());

        return bookingDTOFactory.create(bookingToSave);

    }

    @Override
    public boolean deleteBooking(Long bookingId) {
        Optional<Booking> optBooking = bookingRepository.findById(bookingId);
        if (optBooking.isEmpty()) {
            return false;
        }
        bookingRepository.delete(optBooking.get());

        return true;
    }

    @Override
    public List<String> validateBookingData(SaveBookingDTO saveBookingDTO) {
        List<String> validationResult = new ArrayList<>();

        //Property validations
        Optional<Property> optProperty = propertyRepository.findById(saveBookingDTO.getPropertyId());
        if (optProperty.isEmpty()) {
            validationResult.add("The Property doesn't exists.");
        }
        else if (optProperty.get().getUser().getId().equals(saveBookingDTO.getGuestId())) {
            validationResult.add("The owner cannot reserve his/her property.");
        }

        //Guest validations
        Optional<User> optGuest = userRepository.findById(saveBookingDTO.getGuestId());
        if (optGuest.isEmpty()) {
            validationResult.add("The Guest doesn't exists.");
        }
        else {
            Optional<Role> optRole = optGuest.get().getRoles().stream()
                    .filter(role -> role.getAuthority().equalsIgnoreCase(RoleEnum.ROLE_GUEST.name())).findFirst();
            if (optRole.isEmpty()) {
                validationResult.add("The User is not a Guest.");
            }
        }

        //Dates validations
        if (saveBookingDTO.getCheckin()==null) {
            validationResult.add("Check-in is empty.");
        }
        if (saveBookingDTO.getCheckin().isBefore(LocalDateTime.now())) {
            validationResult.add("Check-in is before Now.");
        }
        if (saveBookingDTO.getCheckout()==null) {
            validationResult.add("Check-out is Empty.");
        }
        if (saveBookingDTO.getCheckout().isBefore(LocalDateTime.now())) {
            validationResult.add("Check-out is before Now.");
        }
        if (saveBookingDTO.getCheckout().isBefore(saveBookingDTO.getCheckin())) {
            validationResult.add("Check-out is Before Check-in.");
        }

        //Booking data validation
        optProperty.ifPresent(property -> validateBookingDates(saveBookingDTO.getCheckin(), saveBookingDTO.getCheckout(),
                saveBookingDTO.getId(), property, validationResult));
        if (saveBookingDTO.getPeopleAmount()==null || saveBookingDTO.getPeopleAmount()==0) {
            validationResult.add("The people amount is not defined.");
        }
        if (saveBookingDTO.getBabysAmount()==null) {
            validationResult.add("The baby's amount is not defined.");
        }
        if (saveBookingDTO.getFoodPreferences()==null) {
            validationResult.add("The food preferences is not defined.");
        }

        return validationResult;
    }

    @Override
    public List<String> validateRebooking(RebookBookingDTO rebookBookingDTO) {
        List<String> validationResult = new ArrayList<>();
        Optional<Booking> optBooking = bookingRepository.findById(rebookBookingDTO.getBookingId());
        if (optBooking.isEmpty()) {
            validationResult.add("The booking doesn't exists.");
        }
        else {
            if (!optBooking.get().getStatus().getName().equalsIgnoreCase(BookingStatusEnum.CANCELED.name())) {
                validationResult.add("The booking is not Canceled.");
            }
            validateBookingDates(rebookBookingDTO.getCheckin(), rebookBookingDTO.getCheckout(),
                    rebookBookingDTO.getBookingId(), optBooking.get().getProperty(), validationResult);
        }

        return validationResult;
    }

    private void validateBookingDates(LocalDateTime checkin, LocalDateTime checkout,
                                              Long bookingId, Property property, List<String> validationResult) {
        Optional<BookingStatus> optBStatus = bookingStatusRepository.findByNameIgnoreCase(BookingStatusEnum.REQUESTED.name());
        List<Booking> bookings = bookingRepository.findByStatusNotAndCheckinBetweenOrCheckoutBetween(
                optBStatus.get(),
                checkin, checkout,
                checkin, checkout
        );
        if (bookingId!=null) {
            bookings = bookings.stream().filter(bookingFilter -> !bookingFilter.getId()
                    .equals(bookingId)).toList();
        }
        if (!bookings.isEmpty()) {
            validationResult.add("The dates selected are already used by other bookings, please select another dates.");
        }
        List<PropertyDateBlock> propertyDateBlocks = propertyDateBlockRepository
                .findByPropertyAndBlockFromBetweenOrBlockToBetween(property, checkin, checkout, checkin, checkout);
        if (!propertyDateBlocks.isEmpty()) {
            validationResult.add("The dates selected are already blocked by the owner, please select another dates.");
        }
    }
}
