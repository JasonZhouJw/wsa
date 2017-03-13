package com.alpha.common.enums;

/**
 * Created by jzhou237 on 2016-09-21.
 */
public enum ResultType {

    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ERROR("DANGER");

    private String type;

    ResultType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getType() {
        return type;
    }
}
