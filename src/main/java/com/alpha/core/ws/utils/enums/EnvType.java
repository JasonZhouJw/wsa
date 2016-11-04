package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 2016-09-28.
 */
// TODO: 2016-11-01 should be removed in the future, environment information should be stored in the memory
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

    public static EnvType getEnvType(String type) {
        EnvType result = DEV;
        for (EnvType temp : EnvType.values()) {
            if (temp.type.equals(type))
                result = temp;
        }
        return result;
    }
}
