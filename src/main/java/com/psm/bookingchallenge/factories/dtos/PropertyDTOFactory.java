package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyDTOFactory {

    @Autowired
    private UserDTOFactory userDTOFactory;

    public PropertyDTO create(Long id, String description, String address, UserDTO user, List<PropertyDateBlockDTO> dateBlocks) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(id);
        propertyDTO.setDescription(description);
        propertyDTO.setAddress(address);
        propertyDTO.setUser(user);

        return propertyDTO;
    }

    public PropertyDTO create(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setUser(userDTOFactory.create(property.getUser()));

        return propertyDTO;
    }

    public List<PropertyDTO> createList(List<Property> properties) {
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        properties.forEach(propertyDTO -> propertyDTOs.add(create(propertyDTO)));

        return propertyDTOs;
    }
}
