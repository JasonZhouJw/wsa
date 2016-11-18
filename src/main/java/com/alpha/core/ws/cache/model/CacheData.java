package com.alpha.core.ws.cache.model;

/**
 * Created by jzhou237 on 2016-11-09.
 */
public class CacheData {

    private String type;

    private Object value;

    private int hit;

    public CacheData() {

    }

    public CacheData(String type, Object value) {
        this.type = type;
        this.value = value;
        this.hit = 0;
    }

    public Object getValue() {
        this.hit++;
        return value;
    }

    public void setValue(Object value) {
        if (!this.value.equals(value)) {
            this.value = value;
        }
    }

    public int getHit() {
        return hit;
    }

    public void resetHit() {
        this.hit = 0;
    }

    public String getType() {
        return type;
    }
}
