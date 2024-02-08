package com.psm.bookingchallenge.factories.models;

import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.PropertyDateBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyDateBlockFactory {

    @Autowired
    private PropertyFactory propertyFactory;

    public PropertyDateBlock create(Long id, Property property, LocalDateTime blockFrom, LocalDateTime blockTo) {
        PropertyDateBlock propertyDateBlock = new PropertyDateBlock();
        propertyDateBlock.setId(id);
        propertyDateBlock.setProperty(property);
        propertyDateBlock.setBlockFrom(blockFrom);
        propertyDateBlock.setBlockTo(blockTo);

        return propertyDateBlock;
    }

    public PropertyDateBlock create(PropertyDateBlockDTO propertyDateBlockDTO) {
        PropertyDateBlock propertyDateBlock = new PropertyDateBlock();
        if (propertyDateBlockDTO.getId()!=null) {
            propertyDateBlock.setId(propertyDateBlockDTO.getId());
        }
        if (propertyDateBlockDTO.getProperty()!=null) {
            propertyDateBlock.setProperty(propertyFactory.create(propertyDateBlockDTO.getProperty()));
        }
        propertyDateBlock.setBlockFrom(propertyDateBlockDTO.getBlockFrom());
        propertyDateBlock.setBlockTo(propertyDateBlockDTO.getBlockTo());

        return propertyDateBlock;
    }

    public List<PropertyDateBlock> createList(List<PropertyDateBlockDTO> propertyDateBlockDTOs) {
        List<PropertyDateBlock> propertyDateBlocks = new ArrayList<>();
        propertyDateBlockDTOs.forEach(propertyDateBlockDTO -> propertyDateBlocks.add(create(propertyDateBlockDTO)));

        return propertyDateBlocks;
    }
}
