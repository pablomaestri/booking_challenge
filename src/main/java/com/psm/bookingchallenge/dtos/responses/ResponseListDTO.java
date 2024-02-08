package com.psm.bookingchallenge.dtos.responses;


import java.util.List;

public class ResponseListDTO {

    private List<? extends DataDTO> data;
    private ErrorDTO error;

    public List<? extends DataDTO> getData() {
        return data;
    }

    public void setData(List<? extends DataDTO> data) {
        this.data = data;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
