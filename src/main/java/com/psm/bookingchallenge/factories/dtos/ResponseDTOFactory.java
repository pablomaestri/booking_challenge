package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.responses.DataDTO;
import com.psm.bookingchallenge.dtos.responses.ErrorDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDTOFactory {

    public ResponseDTO create(DataDTO dataDTO, List<String> errors) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (dataDTO!=null) {
            responseDTO.setData(dataDTO);
        }
        if (errors!=null) {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrors(errors);
            responseDTO.setError(errorDTO);
        }

        return responseDTO;
    }

}
