package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 2016-09-21.
 */
public enum ResultType {

    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ERROR("ERROR");

    private String type;

    ResultType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResultType{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }
}
