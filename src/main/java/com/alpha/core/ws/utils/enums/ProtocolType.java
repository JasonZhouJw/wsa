package com.alpha.core.ws.utils.enums;

/**
 * Created by jzhou237 on 2016-09-30.
 */
public enum ProtocolType {

    WEB_SERVICE("WS");

    private String type;

    ProtocolType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public String getType() {
        return type;
    }
}
