package com.psm.bookingchallenge.dtos.requests;

import java.time.LocalDateTime;

public class SaveBookingDTO {
    private Long id;
    private Long propertyId;
    private Long guestId;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
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

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
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
