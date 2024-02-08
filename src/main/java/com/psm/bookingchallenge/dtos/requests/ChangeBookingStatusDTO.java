package com.psm.bookingchallenge.dtos.requests;

public class ChangeBookingStatusDTO {
    private Long bookingId;
    private Long bookingStatusId;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Long bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }
}
