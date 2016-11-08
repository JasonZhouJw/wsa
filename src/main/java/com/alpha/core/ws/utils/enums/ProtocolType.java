package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 2016-09-30.
 */
public enum ProtocolType {

    WEB_SERVICE("WS", "Web Services");

    private String type;

    private String description;

    ProtocolType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
