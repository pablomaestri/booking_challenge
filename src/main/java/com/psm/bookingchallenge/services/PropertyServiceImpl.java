package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.factories.dtos.PropertyDTOFactory;
import com.psm.bookingchallenge.factories.models.PropertyFactory;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.User;
import com.psm.bookingchallenge.repositories.PropertyRepository;
import com.psm.bookingchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {


    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyDTOFactory propertyDTOFactory;
    @Autowired
    private PropertyFactory propertyFactory;

    @Override
    public PropertyDTO getProperty(Long propertyId) {

        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        return optionalProperty.map(property -> propertyDTOFactory.create(property)).orElse(null);
    }

    @Override
    public List<PropertyDTO> getPropertiesByOwner(Long ownerId) {
        Optional<User> optUser = userRepository.findById(ownerId);
        if (optUser.isEmpty()) {
            return Collections.emptyList();
        }
        List<Property> properties = propertyRepository.findByUser(optUser.get());
        return propertyDTOFactory.createList(properties);
    }

    @Override
    public PropertyDTO saveProperty(Long ownerId, PropertyDTO propertyDTO) {
        Optional<User> optUser = userRepository.findById(ownerId);
        if (optUser.isEmpty()) {
            return null;
        }

        Property property = propertyFactory.create(propertyDTO);
        property.setUser(optUser.get());
        property = propertyRepository.save(property);

        return propertyDTOFactory.create(property);
    }

    @Override
    public boolean deleteProperty(Long ownerId, Long propertyId) {
        Optional<Property> optProperty = propertyRepository.findById(propertyId);
        if (optProperty.isEmpty()) {
            return false;
        }
        if (optProperty.get().getUser().getId()!=ownerId) {
            return false;
        }
        propertyRepository.delete(optProperty.get());
        return true;
    }

    @Override
    public List<String> validatePropertyData(Long ownerId, PropertyDTO propertyDTO) {
        List<String> validationResult = new ArrayList<>();
        Optional<User> optUser = userRepository.findById(ownerId);
        if (optUser.isEmpty()) {
            validationResult.add("User doesn't exists.");
        }
        if (propertyDTO.getAddress()==null || propertyDTO.getAddress().isBlank()) {
            validationResult.add("Address is empty.");
        }
        if (propertyDTO.getDescription()==null || propertyDTO.getDescription().isBlank()) {
            validationResult.add("Description is empty.");
        }
        if (propertyDTO.getId()!=null) {
            Optional<Property> optProperty = propertyRepository.findById(propertyDTO.getId());
            if (optProperty.isEmpty()) {
                validationResult.add("Property doesn't exists.");
            }
        }

        return validationResult;
    }
}
