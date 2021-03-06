package com.alpha.common.cache.model;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Created by jzhou237 on 2016-11-09.
 */
public class TypeData {

    private static final int MAX_NUM = 100000;

    private ConcurrentHashMap<String, CacheData> typeData = new ConcurrentHashMap<String, CacheData>();

    private int hit = 0;

    public TypeData() {

    }

    public TypeData(String key, String value) {
        put(key, value);
    }

    public Map<String, CacheData> getTypeData() {
        this.hit++;
        return this.typeData;
    }

    public void put(String key, Object value) {
        CacheData cacheData = this.getCacheDataForAdd(key);
        if (cacheData == null) {
            cacheData = new CacheData(key, value);
        } else {
            cacheData.setValue(value);
        }
        this.typeData.put(key, cacheData);
    }

    public Object get(String key) {
        CacheData cacheData = this.typeData.get(key);
        return cacheData == null ? null : cacheData.getValue();
    }

    private CacheData getCacheDataForAdd(String key) {
        CacheData cacheData = this.typeData.get(key);
        if (cacheData == null && this.typeData.size() >= MAX_NUM) {
            Iterator<Entry<String, CacheData>> iterator = this.typeData.entrySet().iterator();
            CacheData minData = null;
            while (iterator.hasNext()) {
                Entry<String, CacheData> entry = iterator.next();
                if (minData == null || entry.getValue().getHit() < minData.getHit()) {
                    minData = entry.getValue();
                }
            }
            if (minData != null) {
                this.typeData.remove(minData.getType());
            }
        }
        return cacheData;
    }

    public void refresh(Supplier<Map<String, Object>> supplier) {
        Map<String, Object> cacheData = supplier.get();
        this.typeData.clear();
        cacheData.forEach((key, value) -> {
            this.typeData.put(key, new CacheData(key, value));
        });
    }
}
