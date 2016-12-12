package com.alpha.loader;

import com.alpha.loader.entities.ServicesClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-12.
 */
public class ServicesLoader {

    private Map<String, ServicesClass> servicesClassMap = new HashMap<>();

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
