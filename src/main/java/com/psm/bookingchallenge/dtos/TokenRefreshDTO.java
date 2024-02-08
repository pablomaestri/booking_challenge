package com.psm.bookingchallenge.dtos;

public record TokenRefreshDTO(
        String username,
        Boolean refreshed) {
}
