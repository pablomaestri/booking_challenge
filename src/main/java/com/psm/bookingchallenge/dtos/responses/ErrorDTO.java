package com.psm.bookingchallenge.dtos.responses;

import java.util.List;

public class ErrorDTO {
    List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
