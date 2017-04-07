package com.alpha.loader;

import com.alpha.loader.entities.ServicesClass;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jzhou237 on 2016-12-12.
 */
public class ServicesLoader {

    private ConcurrentHashMap<String, ServicesClass> servicesClassMap = new ConcurrentHashMap<>();

    private ServicesLoader() {

    }

    public static ServicesLoader getInstance() {
        return ServiceLoaderInner.instance;
    }

    public void put(Class clazz) {
        Class superClazz = clazz.getSuperclass();
        if (superClazz.getName().equals("javax.xml.ws.Service")) {
            ServicesClass servicesClass = new ServicesClass(clazz);
            servicesClassMap.put(servicesClass.getServiceName(), servicesClass);
        }
    }

    public ServicesClass get(String name) {
        return servicesClassMap.get(name);
    }


    private static class ServiceLoaderInner {
        public static ServicesLoader instance = new ServicesLoader();
    }
}
