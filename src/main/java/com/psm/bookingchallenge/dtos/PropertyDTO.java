package com.psm.bookingchallenge.dtos;

import com.psm.bookingchallenge.dtos.responses.DataDTO;

public class PropertyDTO implements DataDTO {

    private Long id;
    private String description;
    private String address;
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
