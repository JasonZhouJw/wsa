package com.alpha.core.ws.cache;

import com.alpha.core.ws.cache.model.CacheData;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by jzhou237 on 2016-11-09.
 */
public enum Caches {

    CONSTRUCTOR("CONSTRUCTOR"),
    SYSTEM("SYS"),
    ENVIRONMENT("ENV"),
    SERVICES("SERVICES");

    private String type;

    private MemoryCache memoryCache;

    private Caches(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void put(String key, Object value){
        MemoryCache.getInstance().put(this.type, key, value);
    }

    public Object get(String key){
        return MemoryCache.getInstance().get(this.type, key);
    }

    public Map<String, Object> get(){
        return MemoryCache.getInstance().get(this.type);
    }

    public void refresh(Supplier<Map<String, Object>> supplier){
        MemoryCache.getInstance().refresh(this.type, supplier);
    }
}
