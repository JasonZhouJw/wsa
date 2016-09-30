package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 2016-09-28.
 */
public enum EnvType {

    DEV("dev", "Development environment");

    private String type;

    private String name;

    EnvType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return type;
    }
}
