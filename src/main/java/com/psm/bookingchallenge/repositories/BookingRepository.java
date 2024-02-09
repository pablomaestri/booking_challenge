package com.psm.bookingchallenge.repositories;

import com.psm.bookingchallenge.models.Booking;
import com.psm.bookingchallenge.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStatusNotAndCheckinBetweenOrCheckoutBetween(BookingStatus status, LocalDateTime checkin,
                                                                    LocalDateTime checkin2,  LocalDateTime checkout,
                                                                    LocalDateTime checkout2);
}
