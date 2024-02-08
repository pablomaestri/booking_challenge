package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.factories.models.PropertyFactory;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.PropertyDateBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyDateBlockDTOFactory {

    @Autowired
    private PropertyDTOFactory propertyDTOFactory;

    public PropertyDateBlockDTO create(Long id, PropertyDTO property, LocalDateTime blockFrom, LocalDateTime blockTo) {
        PropertyDateBlockDTO propertyDateBlockDTO = new PropertyDateBlockDTO();
        propertyDateBlockDTO.setId(id);
        propertyDateBlockDTO.setProperty(property);
        propertyDateBlockDTO.setBlockFrom(blockFrom);
        propertyDateBlockDTO.setBlockTo(blockTo);

        return propertyDateBlockDTO;
    }

    public PropertyDateBlockDTO create(PropertyDateBlock propertyDateBlock) {
        PropertyDateBlockDTO propertyDateBlockDTO = new PropertyDateBlockDTO();
        propertyDateBlockDTO.setId(propertyDateBlock.getId());
        propertyDateBlockDTO.setProperty(propertyDTOFactory.create(propertyDateBlock.getProperty()));
        propertyDateBlockDTO.setBlockFrom(propertyDateBlock.getBlockFrom());
        propertyDateBlockDTO.setBlockTo(propertyDateBlock.getBlockTo());

        return propertyDateBlockDTO;
    }

    public List<PropertyDateBlockDTO> createList(List<PropertyDateBlock> propertyDateBlocks) {
        List<PropertyDateBlockDTO> propertyDateBlockDTOs = new ArrayList<>();
        propertyDateBlocks.forEach(propertyDateBlock -> propertyDateBlockDTOs.add(create(propertyDateBlock)));

        return propertyDateBlockDTOs;
    }
}
