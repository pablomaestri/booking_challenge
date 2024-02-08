package com.psm.bookingchallenge.dtos;

import com.psm.bookingchallenge.dtos.responses.DataDTO;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BookingDTO implements DataDTO {

    private Long id;
    private PropertyDTO property;
    private UserDTO guest;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private BookingStatusDTO status;
    Integer peopleAmount;
    Integer babysAmount;
    String foodPreferences;
    Boolean hasPets;

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

    public UserDTO getGuest() {
        return guest;
    }

    public void setGuest(UserDTO guest) {
        this.guest = guest;
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

    public BookingStatusDTO getStatus() {
        return status;
    }

    public void setStatus(BookingStatusDTO status) {
        this.status = status;
    }

    public Integer getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(Integer peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public Integer getBabysAmount() {
        return babysAmount;
    }

    public void setBabysAmount(Integer babysAmount) {
        this.babysAmount = babysAmount;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    public Boolean getHasPets() {
        return hasPets;
    }

    public void setHasPets(Boolean hasPets) {
        this.hasPets = hasPets;
    }
}
