package com.psm.bookingchallenge.dtos;

import com.psm.bookingchallenge.dtos.responses.DataDTO;

import java.time.LocalDateTime;

public class PropertyDateBlockDTO implements DataDTO {

    Long id;
    private PropertyDTO property;
    private LocalDateTime blockFrom;
    private LocalDateTime blockTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public LocalDateTime getBlockFrom() {
        return blockFrom;
    }

    public void setBlockFrom(LocalDateTime blockFrom) {
        this.blockFrom = blockFrom;
    }

    public LocalDateTime getBlockTo() {
        return blockTo;
    }

    public void setBlockTo(LocalDateTime blockTo) {
        this.blockTo = blockTo;
    }
}
