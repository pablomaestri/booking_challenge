package com.psm.bookingchallenge.repositories;

import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.PropertyDateBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PropertyDateBlockRepository extends JpaRepository<PropertyDateBlock, Long> {

    List<PropertyDateBlock> findByProperty(Property property);
    List<PropertyDateBlock> findByPropertyAndBlockFromBetweenOrBlockToBetween(Property property, LocalDateTime blockFrom,
                                                                              LocalDateTime blockFrom2, LocalDateTime blockTo,
                                                                              LocalDateTime blockTo2);

}
