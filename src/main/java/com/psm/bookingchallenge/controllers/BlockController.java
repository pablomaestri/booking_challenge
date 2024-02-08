package com.psm.bookingchallenge.controllers;

import com.psm.bookingchallenge.dtos.PropertyDateBlockDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseListDTO;
import com.psm.bookingchallenge.factories.dtos.ResponseDTOFactory;
import com.psm.bookingchallenge.factories.dtos.ResponseListDTOFactory;
import com.psm.bookingchallenge.services.PropertyDateBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/date_blocks")
public class BlockController {

    @Autowired
    private PropertyDateBlockService propertyDateBlockService;
    @Autowired
    private ResponseDTOFactory responseDTOFactory;
    @Autowired
    private ResponseListDTOFactory responseListDTOFactory;

    @GetMapping("/{dateBlockId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> getDateBlock(@PathVariable Long dateBlockId) {

        PropertyDateBlockDTO dateBlockDTO = propertyDateBlockService.getDateBlock(dateBlockId);
        ResponseDTO responseDTO = responseDTOFactory.create(dateBlockDTO, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseListDTO> getDateBlocksByProperty(@PathVariable Long propertyId) {
        List<PropertyDateBlockDTO> propertyDateBlockDTOs = propertyDateBlockService.getDateBlocksByProperty(propertyId);

        ResponseListDTO responseDTO = responseListDTOFactory.create(propertyDateBlockDTOs, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PostMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> createProperty(@PathVariable Long propertyId,
                                                      @RequestBody PropertyDateBlockDTO propertyDateBlockDTO) {
        return saveDateBlock(propertyId, propertyDateBlockDTO);

    }

    @PutMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> editProperty(@PathVariable Long propertyId,
                                                    @RequestBody PropertyDateBlockDTO propertyDateBlockDTO) {
        return saveDateBlock(propertyId, propertyDateBlockDTO);
    }

    private ResponseEntity<ResponseDTO> saveDateBlock(Long propertyId, PropertyDateBlockDTO propertyDateBlockDTO) {
        List<String> errors = propertyDateBlockService.validateDateBlockData(propertyId, propertyDateBlockDTO);
        if (errors.isEmpty()) {
            PropertyDateBlockDTO dateBlockDTOCreated = propertyDateBlockService
                    .saveDateBlock(propertyId, propertyDateBlockDTO);
            ResponseDTO responseDTO = responseDTOFactory.create(dateBlockDTOCreated, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        ResponseDTO responseDTO = responseDTOFactory.create(null, errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{dateBlockId}/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<ResponseDTO> deleteDateBlock(@PathVariable Long dateBlockId, @PathVariable Long propertyId) {

        boolean response = propertyDateBlockService.deleteDateBlock(propertyId, dateBlockId);
        if (response) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        List<String> errors = List.of("There was an error trying to delete the Date Block.");
        ResponseDTO responseDTO = responseDTOFactory.create(null, errors);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
