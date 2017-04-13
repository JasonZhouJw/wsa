package com.alpha.loader;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jzhou237 on 2016-12-09.
 */
public class ClassCache {

    private ClassCache classCache;

    private ConcurrentHashMap<String, Class> classData = new ConcurrentHashMap<>();


    private ClassCache() {
    }

    public static ClassCache getInstance() {
        return ClassCacheInner.instance;
    }

    public Class addClass(Class clazz) {
        classData.put(clazz.getName(), clazz);
        return clazz;
    }

    public Class loadClass(CustomClassLoad customClassLoad, String name) throws ClassNotFoundException {
        return this.addClass(customClassLoad.loadCustomClass(name));
    }

    public Class getClass(String name) throws ClassNotFoundException {
        Class existClass = this.classData.get(name);
        if (existClass == null) {
            throw new ClassNotFoundException();
        }
        return existClass;
    }

    public ConcurrentHashMap<String, Class> getClassData() {
        return classData;
    }

    private static class ClassCacheInner {
        static final ClassCache instance = new ClassCache();
    }
}
