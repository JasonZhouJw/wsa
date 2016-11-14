package com.alpha.core.ws.cache;

import com.alpha.core.ws.cache.model.TypeData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by jzhou237 on 2016-11-09.
 */
public class MemoryCache {

    private Map<String, TypeData> dataMap = new HashMap<String, TypeData>();

    private MemoryCache() {
    }

    public static MemoryCache getInstance() {
        return Cache.memoryCache;
    }

    public TypeData init(String type) {
        if (!this.dataMap.containsKey(type)) {
            this.dataMap.put(type, new TypeData());
        }
        return this.dataMap.get(type);
    }

    public void put(String type, String key, String value) {
        init(type).put(key, value);
    }

    public String get(String type, String key) {
        return init(type).get(key);
    }

    public Map<String, String> get(String type) {
        Map<String, String> dataMap = new HashMap<String, String>();
        init(type).getTypeData().forEach((key, cacheData) -> {
            dataMap.put(key, cacheData.getValue());
        });
        return dataMap;
    }

    public void refresh(String type, Supplier<Map<String, String>> supplier) {
        init(type).refresh(supplier);
    }

    private static class Cache {
        static final MemoryCache memoryCache = new MemoryCache();
    }

}
