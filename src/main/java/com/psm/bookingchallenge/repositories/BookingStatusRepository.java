package com.psm.bookingchallenge.repositories;

import com.psm.bookingchallenge.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {

    Optional<BookingStatus> findByNameIgnoreCase(String name);
}
