package com.alpha.core.common.utils.enums;

/**
 * Created by jzhou237 on 2016-09-28.
 */
// TODO: 2016-11-01 should be removed in the future, environment information should be stored in the memory
public enum EnvType {

    DEV("dev", "Development environment");

    private String type;

    private String description;

    EnvType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public static EnvType getEnvType(String type) {
        EnvType result = DEV;
        for (EnvType temp : EnvType.values()) {
            if (temp.type.equals(type))
                result = temp;
        }
        return result;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
