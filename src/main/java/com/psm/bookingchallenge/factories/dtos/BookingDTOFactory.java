package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.BookingDTO;
import com.psm.bookingchallenge.dtos.BookingStatusDTO;
import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDTOFactory {

    @Autowired
    private PropertyDTOFactory propertyDTOFactory;
    @Autowired
    private UserDTOFactory userDTOFactory;
    @Autowired
    private BookingStatusDTOFactory bookingStatusDTOFactory;

    public BookingDTO create(Long id, PropertyDTO property, UserDTO guest, LocalDateTime checkin, LocalDateTime checkout,
                             BookingStatusDTO status, Integer peopleAmount, Integer babysAmount, String foodPreferences,
                             Boolean hasPets) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(id);
        bookingDTO.setProperty(property);
        bookingDTO.setGuest(guest);
        bookingDTO.setCheckin(checkin);
        bookingDTO.setCheckout(checkout);
        bookingDTO.setStatus(status);
        bookingDTO.setPeopleAmount(peopleAmount);
        bookingDTO.setBabysAmount(babysAmount);
        bookingDTO.setFoodPreferences(foodPreferences);
        bookingDTO.setHasPets(hasPets);

        return bookingDTO;
    }

    public BookingDTO create(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setProperty(propertyDTOFactory.create(booking.getProperty()));
        bookingDTO.setGuest(userDTOFactory.create(booking.getGuest()));
        bookingDTO.setCheckin(booking.getCheckin());
        bookingDTO.setCheckout(booking.getCheckout());
        bookingDTO.setStatus(bookingStatusDTOFactory.create(booking.getStatus()));
        bookingDTO.setPeopleAmount(booking.getPeopleAmount());
        bookingDTO.setBabysAmount(booking.getBabysAmount());
        bookingDTO.setFoodPreferences(booking.getFoodPreferences());
        bookingDTO.setHasPets(booking.getHasPets());

        return bookingDTO;
    }

    public List<BookingDTO> createList(List<Booking> bookings) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        bookings.forEach(booking -> bookingDTOs.add(create(booking)));

        return bookingDTOs;
    }
}
