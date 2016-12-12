package com.alpha.common.utils;

/**
 * Created by jzhou237 on 2016-11-02.
 */
public enum ResponseType {

    SUCCESS("000000", "Success"),
    VALIDATION_ERROR("000001", "Validation Error."),
    ELEMENTS_NOT_FOUND("000002", "Can not find the data."),
    UNKNOWN_ERROR("999999", "System Error");

    private String code;

    private String message;

    ResponseType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static boolean isSuccess(ResponseType responseType) {
        return isSuccess(responseType.getCode());
    }

    public static boolean isSuccess(String code) {
        return SUCCESS.getCode().equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
