package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.BookingStatusDTO;
import com.psm.bookingchallenge.models.BookingStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingStatusDTOFactory {

    public BookingStatusDTO create(Long id, String name, String description) {
        BookingStatusDTO bookingStatusDTO = new BookingStatusDTO();
        bookingStatusDTO.setId(id);
        bookingStatusDTO.setName(name);
        bookingStatusDTO.setDescription(description);

        return bookingStatusDTO;
    }

    public BookingStatusDTO create(BookingStatus bookingStatus) {
        BookingStatusDTO bookingStatusDTO = new BookingStatusDTO();
        bookingStatusDTO.setId(bookingStatus.getId());
        bookingStatusDTO.setName(bookingStatus.getName());
        bookingStatusDTO.setDescription(bookingStatus.getDescription());

        return bookingStatusDTO;
    }

    public List<BookingStatusDTO> createList(List<BookingStatus> bookingStatuss) {
        List<BookingStatusDTO> bookingStatusDTOs = new ArrayList<>();
        bookingStatuss.forEach(bookingStatus -> bookingStatusDTOs.add(create(bookingStatus)));

        return bookingStatusDTOs;
    }
}
