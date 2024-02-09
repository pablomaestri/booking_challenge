package com.psm.bookingchallenge.services;

import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.factories.dtos.PropertyDateBlockDTOFactory;
import com.psm.bookingchallenge.factories.models.PropertyDateBlockFactory;
import com.psm.bookingchallenge.models.Property;
import com.psm.bookingchallenge.models.PropertyDateBlock;
import com.psm.bookingchallenge.repositories.PropertyDateBlockRepository;
import com.psm.bookingchallenge.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyDateBlockServiceImpl implements PropertyDateBlockService{


    @Autowired
    private PropertyDateBlockRepository propertyDateBlockRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyDateBlockDTOFactory propertyDateBlockDTOFactory;
    @Autowired
    private PropertyDateBlockFactory propertyDateBlockFactory;

    @Override
    public PropertyDateBlockDTO getDateBlock(Long dateBlockId) {
        Optional<PropertyDateBlock> optionalPDB = propertyDateBlockRepository.findById(dateBlockId);
        return optionalPDB.map(propertyDateBlock -> propertyDateBlockDTOFactory.create(propertyDateBlock))
                .orElse(null);
    }

    @Override
    public List<PropertyDateBlockDTO> getDateBlocksByProperty(Long propertyId) {
        Optional<Property> optProperty = propertyRepository.findById(propertyId);
        if (optProperty.isEmpty()) {
            return Collections.emptyList();
        }
        List<PropertyDateBlock> propertyDateBlocks = propertyDateBlockRepository.findByProperty(optProperty.get());
        return propertyDateBlockDTOFactory.createList(propertyDateBlocks);
    }

    @Override
    public PropertyDateBlockDTO saveDateBlock(Long propertyId, PropertyDateBlockDTO propertyDateBlockDTO) {
        Optional<Property> optProperty = propertyRepository.findById(propertyId);
        if (optProperty.isEmpty()) {
            return null;
        }
        PropertyDateBlock propertyDateBlock = propertyDateBlockFactory.create(propertyDateBlockDTO);
        propertyDateBlock.setProperty(optProperty.get());
        propertyDateBlock = propertyDateBlockRepository.save(propertyDateBlock);

        return propertyDateBlockDTOFactory.create(propertyDateBlock);
    }

    @Override
    public boolean deleteDateBlock(Long propertyId, Long dateBlockId) {
        Optional<PropertyDateBlock> optionalPDB = propertyDateBlockRepository.findById(dateBlockId);
        if (optionalPDB.isEmpty()) {
            return false;
        }
        if (optionalPDB.get().getProperty().getId()!=propertyId) {
            return false;
        }
        propertyDateBlockRepository.delete(optionalPDB.get());
        return true;
    }

    @Override
    public List<String> validateDateBlockData(Long propertyId, PropertyDateBlockDTO propertyDateBlockDTO) {
        List<String> validationResult = new ArrayList<>();
        Optional<Property> optProperty = propertyRepository.findById(propertyId);
        if (optProperty.isEmpty()) {
            validationResult.add("Property doesn't exists.");
        }
        if (propertyDateBlockDTO.getBlockFrom()==null) {
            validationResult.add("Date From is empty.");
        }
        if (propertyDateBlockDTO.getBlockFrom().isBefore(LocalDateTime.now())) {
            validationResult.add("Date From is before Now.");
        }
        if (propertyDateBlockDTO.getBlockTo()==null) {
            validationResult.add("Date To is Empty.");
        }
        if (propertyDateBlockDTO.getBlockTo().isBefore(LocalDateTime.now())) {
            validationResult.add("Date To is before Now.");
        }
        if (propertyDateBlockDTO.getBlockTo().isBefore(propertyDateBlockDTO.getBlockFrom())) {
            validationResult.add("Date To is Before Date From.");
        }
        if (propertyDateBlockDTO.getId()!=null) {
            Optional<PropertyDateBlock> optPDB = propertyDateBlockRepository.findById(propertyDateBlockDTO.getId());
            if (optPDB.isEmpty()) {
                validationResult.add("Date Block doesn't exists.");
            }
        }

        return validationResult;
    }
}
