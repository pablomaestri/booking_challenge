package com.psm.bookingchallenge.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "property_id", nullable = false)
        private Property property;
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "guest_id", nullable = false)
        private User guest;
        @Column(name = "date_from")
        private LocalDateTime checkin;
        @Column(name = "date_to")
        private LocalDateTime checkout;
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "booking_status_id", nullable = false)
        BookingStatus status;
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

        public Property getProperty() {
                return property;
        }

        public void setProperty(Property property) {
                this.property = property;
        }

        public User getGuest() {
                return guest;
        }

        public void setGuest(User guest) {
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

        public BookingStatus getStatus() {
                return status;
        }

        public void setStatus(BookingStatus status) {
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
