package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.PropertyDateBlock;
import com.psm.bookingchallenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyFactory {

    @Autowired
    private UserFactory userFactory;

    public Property create(Long id, String description, String address, User user, List<PropertyDateBlock> dateBlocks) {
        Property property = new Property();
        property.setId(id);
        property.setDescription(description);
        property.setAddress(address);
        property.setUser(user);

        return property;
    }

    public Property create(PropertyDTO propertyDTO) {
        Property property = new Property();
        if (propertyDTO.getId()!=null) {
            property.setId(propertyDTO.getId());
        }

        property.setDescription(propertyDTO.getDescription());
        property.setAddress(propertyDTO.getAddress());
        if (propertyDTO.getUser()!=null) {
            property.setUser(userFactory.create(propertyDTO.getUser()));
        }

        return property;
    }

    public List<Property> createList(List<PropertyDTO> propertyDTOs) {
        List<Property> properties = new ArrayList<>();
        propertyDTOs.forEach(propertyDTO -> properties.add(create(propertyDTO)));

        return properties;
    }
}
