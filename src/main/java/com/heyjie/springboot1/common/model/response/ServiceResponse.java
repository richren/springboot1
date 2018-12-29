package com.heyjie.springboot1.common.model.response;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer SUCCESS_CODE = 200;
    private Integer code;
    private String message;

    public ServiceResponse() {
        this(SUCCESS_CODE, "");
    }

    public ServiceResponse(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
