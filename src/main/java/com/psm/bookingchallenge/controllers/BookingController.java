package com.psm.bookingchallenge.controllers;

import com.psm.bookingchallenge.dtos.BookingDTO;
import com.psm.bookingchallenge.dtos.requests.ChangeBookingStatusDTO;
import com.psm.bookingchallenge.dtos.requests.RebookBookingDTO;
import com.psm.bookingchallenge.dtos.requests.SaveBookingDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseDTO;
import com.psm.bookingchallenge.factories.dtos.ResponseDTOFactory;
import com.psm.bookingchallenge.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private ResponseDTOFactory responseDTOFactory;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getBooking(@PathVariable("id") Long id) {
        BookingDTO bookingDTO = bookingService.getBooking(id);
        return new ResponseEntity<>(responseDTOFactory.create(bookingDTO, null), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<ResponseDTO> createBooking(@RequestBody SaveBookingDTO saveBookingDTO) {
            return saveBooking(saveBookingDTO);
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> updateBooking(@RequestBody SaveBookingDTO saveBookingDTO) {
        return saveBooking(saveBookingDTO);
    }

    @PatchMapping("/status")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> changeBookingStatus(@RequestBody ChangeBookingStatusDTO changeBookingStatusDTO) {
        BookingDTO bookingDTO = bookingService.changeBookingStatus(changeBookingStatusDTO);
        return new ResponseEntity<>(responseDTOFactory.create(bookingDTO, null), HttpStatus.OK);
    }

    @PutMapping("/rebook")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<ResponseDTO> rebookBooking(@RequestBody RebookBookingDTO rebookBookingDTO) {
        List<String> validationResult = bookingService.validateRebooking(rebookBookingDTO);
        if (validationResult.isEmpty()) {
            BookingDTO bookingDTO = bookingService.rebookBooking(rebookBookingDTO);
            return new ResponseEntity<>(responseDTOFactory.create(bookingDTO, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTOFactory.create(null, validationResult), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable("id") Long id) {
        boolean response = bookingService.deleteBooking(id);
        if (response) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        List<String> errors = List.of("There was an error trying to delete the Booking.");
        ResponseDTO responseDTO = responseDTOFactory.create(null, errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseDTO> saveBooking(SaveBookingDTO saveBookingDTO) {
        List<String> validationResult = bookingService.validateBookingData(saveBookingDTO);
        if (validationResult.isEmpty()) {
            BookingDTO bookingDTO = bookingService.saveBooking(saveBookingDTO);
            return new ResponseEntity<>(responseDTOFactory.create(bookingDTO, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTOFactory.create(null, validationResult), HttpStatus.BAD_REQUEST);
    }

}
