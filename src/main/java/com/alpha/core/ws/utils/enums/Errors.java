package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public enum Errors {

    CLASS_NOT_FOUND("0001", "Class Not found."),
    UNKNOWN("9999", "Unknown Error.");

    private String code;

    private String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
