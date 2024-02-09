package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.BookingDTO;
import com.psm.bookingchallenge.dtos.requests.ChangeBookingStatusDTO;
import com.psm.bookingchallenge.dtos.requests.RebookBookingDTO;
import com.psm.bookingchallenge.dtos.requests.SaveBookingDTO;
import java.util.List;

public interface BookingService {

    BookingDTO getBooking(Long bookingId);
    BookingDTO saveBooking(SaveBookingDTO saveBookingDTO);
    BookingDTO changeBookingStatus(ChangeBookingStatusDTO changeBookingStatusDTO);
    BookingDTO rebookBooking(RebookBookingDTO rebookBookingDTO);
    boolean deleteBooking(Long bookingId);
    List<String> validateBookingData(SaveBookingDTO saveBookingDTO);
    List<String> validateRebooking(RebookBookingDTO rebookBookingDTO);

}
