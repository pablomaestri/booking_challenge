package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import java.util.List;

public interface PropertyDateBlockService {

    PropertyDateBlockDTO getDateBlock(Long dateBlockId);
    List<PropertyDateBlockDTO> getDateBlocksByProperty(Long propertyId);
    PropertyDateBlockDTO saveDateBlock(Long propertyId, PropertyDateBlockDTO propertyDateBlockDTO);
    boolean deleteDateBlock(Long propertyId, Long dateBlockId);
    List<String> validateDateBlockData(Long propertyId, PropertyDateBlockDTO propertyDateBlockDTO);
}
