package com.heyjie.springboot1.common.model.response;

public class DataResponse<T> extends ServiceResponse {
    private static final long serialVersionUID = 1L;
    private T data;

    public DataResponse() {
    }

    public DataResponse(String message) {
        super(DataResponseCode.SUCCESS.getCode(), message);
    }

    public DataResponse(T data) {
        this(DataResponseCode.SUCCESS, data);
    }

    public DataResponse(DataResponseCode responseCode) {
        super(responseCode.getCode(), responseCode.getMessage());
    }

    public DataResponse(DataResponseCode responseCode, T data) {
        super(responseCode.getCode(), responseCode.getMessage());
        this.setData(data);
    }

    public DataResponse(Integer code, String message, T data) {
        super(code, message);
        this.setData(data);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
