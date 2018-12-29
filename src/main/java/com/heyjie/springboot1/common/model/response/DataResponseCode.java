package com.heyjie.springboot1.common.model.response;

public enum DataResponseCode {
    SUCCESS(200, "成功"),
    Moved(300, "资源已转移"),
    BAD_REQUEST(400, "错误的请求"),
    INTERNAL_SERVER_ERROR(500, "服务器内部出错"),
    RECORD_ALREADY_EXISTS(10000, "记录已存在"),
    RECORD_DOES_NOT_EXISTS(10001, "记录不存在"),
    ;

    private Integer code;
    private String message;

    DataResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
