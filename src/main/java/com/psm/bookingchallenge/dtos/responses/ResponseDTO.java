package com.psm.bookingchallenge.dtos.responses;

import com.psm.bookingchallenge.dtos.responses.DataDTO;
import com.psm.bookingchallenge.dtos.responses.ErrorDTO;

public class ResponseDTO {
    private DataDTO data;
    private ErrorDTO error;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
