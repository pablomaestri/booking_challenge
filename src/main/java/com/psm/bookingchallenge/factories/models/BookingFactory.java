package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.BookingDTO;
import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.factories.dtos.PropertyDTOFactory;
import com.psm.bookingchallenge.factories.dtos.UserDTOFactory;
import com.psm.bookingchallenge.models.Booking;
import com.psm.bookingchallenge.models.BookingStatus;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingFactory {

    @Autowired
    private PropertyFactory propertyFactory;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private BookingStatusFactory bookingStatusFactory;

    public Booking create(Long id, Property property, User guest, LocalDateTime checkin, LocalDateTime checkout,
                          BookingStatus status, Integer peopleAmount, Integer babysAmount, String foodPreferences,
                          Boolean hasPets ) {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setProperty(property);
        booking.setGuest(guest);
        booking.setCheckin(checkin);
        booking.setCheckout(checkout);
        booking.setStatus(status);
        booking.setPeopleAmount(peopleAmount);
        booking.setBabysAmount(babysAmount);
        booking.setFoodPreferences(foodPreferences);
        booking.setHasPets(hasPets);

        return booking;
    }

    public Booking create(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setProperty(propertyFactory.create(bookingDTO.getProperty()));
        booking.setGuest(userFactory.create(bookingDTO.getGuest()));
        booking.setCheckin(bookingDTO.getCheckin());
        booking.setCheckout(bookingDTO.getCheckout());
        booking.setStatus(bookingStatusFactory.create(bookingDTO.getStatus()));
        booking.setPeopleAmount(bookingDTO.getPeopleAmount());
        booking.setBabysAmount(bookingDTO.getBabysAmount());
        booking.setFoodPreferences(bookingDTO.getFoodPreferences());
        booking.setHasPets(bookingDTO.getHasPets());

        return booking;
    }

    public List<Booking> createList(List<BookingDTO> bookingDTOs) {
        List<Booking> bookings = new ArrayList<>();
        bookingDTOs.forEach(bookingDTO -> bookings.add(create(bookingDTO)));

        return bookings;
    }

}
