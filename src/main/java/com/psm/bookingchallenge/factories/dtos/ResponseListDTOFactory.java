package com.psm.bookingchallenge.factories.dtos;

import com.psm.bookingchallenge.dtos.responses.DataDTO;
import com.psm.bookingchallenge.dtos.responses.ErrorDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseListDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseListDTOFactory {

    public ResponseListDTO create(List<? extends DataDTO> datasDTO, List<String> errors) {
        ResponseListDTO responseListDTO = new ResponseListDTO();
        if (datasDTO!=null) {
            responseListDTO.setData(datasDTO);
        }
        if (errors!=null) {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrors(errors);
            responseListDTO.setError(errorDTO);
        }

        return responseListDTO;
    }

}
