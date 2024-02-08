package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.Property;

import java.util.List;

public interface PropertyService {

    PropertyDTO getProperty(Long propertyId);
    List<PropertyDTO> getPropertiesByOwner(Long ownerId);
    PropertyDTO saveProperty(Long ownerId, PropertyDTO propertyDTO);
    boolean deleteProperty(Long ownerId, Long propertyId);
    List<String> validatePropertyData(Long ownerId, PropertyDTO propertyDTO);
}
