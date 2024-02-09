package com.psm.bookingchallenge.controllers;

import com.psm.bookingchallenge.dtos.PropertyDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseListDTO;
import com.psm.bookingchallenge.factories.dtos.ResponseDTOFactory;
import com.psm.bookingchallenge.factories.dtos.ResponseListDTOFactory;
import com.psm.bookingchallenge.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller()
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ResponseDTOFactory responseDTOFactory;
    @Autowired
    private ResponseListDTOFactory responseListDTOFactory;

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> getProperty(@PathVariable Long propertyId) {

        PropertyDTO propertyDTO = propertyService.getProperty(propertyId);
        ResponseDTO responseDTO = responseDTOFactory.create(propertyDTO, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseListDTO> getPropertiesByOwner(@PathVariable Long ownerId) {
        List<PropertyDTO> propertyDTOs = propertyService.getPropertiesByOwner(ownerId);

        ResponseListDTO responseDTO = responseListDTOFactory.create(propertyDTOs, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PostMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> createProperty(@PathVariable Long ownerId, @RequestBody PropertyDTO propertyDTO) {
        return saveProperty(ownerId, propertyDTO);

    }

    @PutMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> editProperty(@PathVariable Long ownerId, @RequestBody PropertyDTO propertyDTO) {
        return saveProperty(ownerId, propertyDTO);
    }

    @DeleteMapping("/{propertyId}/owner/{ownerId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> deleteProperty(@PathVariable Long propertyId, @PathVariable Long ownerId) {

        boolean response = propertyService.deleteProperty(ownerId, propertyId);
        if (response) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        List<String> errors = List.of("There was an error trying to delete the Property.");
        ResponseDTO responseDTO = responseDTOFactory.create(null, errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseDTO> saveProperty(Long ownerId, PropertyDTO propertyDTO) {
        List<String> errors = propertyService.validatePropertyData(ownerId, propertyDTO);
        if (errors.isEmpty()) {
            PropertyDTO propertyDTOCreated = propertyService.saveProperty(ownerId, propertyDTO);
            ResponseDTO responseDTO = responseDTOFactory.create(propertyDTOCreated, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        ResponseDTO responseDTO = responseDTOFactory.create(null, errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }




}
