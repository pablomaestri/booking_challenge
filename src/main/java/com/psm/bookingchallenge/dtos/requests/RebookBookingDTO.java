package com.psm.bookingchallenge.dtos.requests;

import java.time.LocalDateTime;

public class RebookBookingDTO {
    private Long bookingId;
    private LocalDateTime checkin;
    private LocalDateTime checkout;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }
}
