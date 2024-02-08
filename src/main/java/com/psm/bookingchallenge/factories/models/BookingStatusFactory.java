package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.BookingStatusDTO;
import com.psm.bookingchallenge.models.BookingStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingStatusFactory {

    public BookingStatus create(Long id, String name, String description) {
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setId(id);
        bookingStatus.setName(name);
        bookingStatus.setDescription(description);

        return bookingStatus;
    }

    public BookingStatus create(BookingStatusDTO bookingStatusDTO) {
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setId(bookingStatusDTO.getId());
        bookingStatus.setName(bookingStatusDTO.getName());
        bookingStatus.setDescription(bookingStatusDTO.getDescription());

        return bookingStatus;
    }

    public List<BookingStatus> createList(List<BookingStatusDTO> bookingStatusDTOs) {
        List<BookingStatus> bookingStatuses = new ArrayList<>();
        bookingStatusDTOs.forEach(bookingStatusDTO -> bookingStatuses.add(create(bookingStatusDTO)));

        return bookingStatuses;
    }
}
