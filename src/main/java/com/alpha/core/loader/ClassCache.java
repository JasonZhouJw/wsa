package com.alpha.core.loader;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-09.
 */
public class ClassCache {

    private ClassCache classCache;

    private CustomClassLoad customClassLoad;

    private Map<String, Class> classData = new HashMap<>();

    private ClassCache() {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        customClassLoad = new CustomClassLoad(applicationContext.getEnvironment().getProperty("cxf.compilerPath"));
    }

    public static ClassCache getInstance() {
        return ClassCacheInner.instance;
    }

    public Class addClass(Class clazz) {
        return classData.putIfAbsent(clazz.getName(), clazz);
    }

    public Class addClass(String name) throws ClassNotFoundException {
        return this.addClass(customClassLoad.loadCustomClass(name));
    }

    public Class getClass(String name) throws ClassNotFoundException {
        return this.getClass(name, true);
    }

    public Class getClass(String name, boolean reload) throws ClassNotFoundException {
        Class existClass = this.classData.get(name);
        if (existClass == null && reload) {
            existClass = addClass(name);
        }
        return existClass;
    }

    private static class ClassCacheInner {
        static final ClassCache instance = new ClassCache();
    }
}
