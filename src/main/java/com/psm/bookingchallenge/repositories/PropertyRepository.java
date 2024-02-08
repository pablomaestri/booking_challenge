package com.psm.bookingchallenge.repositories;

import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByUser(User user);


}
