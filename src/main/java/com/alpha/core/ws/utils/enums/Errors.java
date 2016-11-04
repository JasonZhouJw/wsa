package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public enum Errors {

    CLASS_NOT_FOUND("0001", "Class Not found."),
    FIELD_NOT_FOUND("0002", "Field Not Found."),
    NULL_POINT("0003", "The Object is null."),
    VERIFY_FAIL("0004", "Verify Fail."),
    FILE_NOT_FOUND("0005", "File Not Found."),
    IO_EXCEPTION("0006", "IO Exception."),
    INVALIDATED_VALUE("0007", "Invalidated value."),
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
