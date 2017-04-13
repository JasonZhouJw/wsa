package com.alpha.loader;

import com.alpha.loader.entities.ServicesClass;

import javax.xml.ws.WebServiceClient;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jzhou237 on 2016-12-12.
 */
public class ServicesLoader {

    /**
     * key: WSDL
     * <p>
     * value: Services Class
     */
    private ConcurrentHashMap<String, ServicesClass> servicesClassMap = new ConcurrentHashMap<>();

    private ServicesLoader() {

    }

    public static ServicesLoader getInstance() {
        return ServiceLoaderInner.instance;
    }

    /**
     * put the class into map when it's ServiceFactory.
     * <p>
     * WsdlLocation is the key, value is ServicesClass
     *
     * @param clazz
     */
    public void put(Class clazz) {
        if (clazz == null) {
            return;
        }
        Class superClazz = clazz.getSuperclass();
        WebServiceClient webServiceClient = (WebServiceClient) clazz.getAnnotation(WebServiceClient.class);
        if (superClazz != null && superClazz.getName().equals("javax.xml.ws.Service") && webServiceClient != null) {
            ServicesClass servicesClass = new ServicesClass(clazz);
            servicesClassMap.put(webServiceClient.wsdlLocation(), servicesClass);
        }
    }

    /**
     * get ServicesClass by WSDL
     *
     * @param name
     * @return
     */
    public ServicesClass get(String name) {
        return servicesClassMap.get(name);
    }


    private static class ServiceLoaderInner {
        public static ServicesLoader instance = new ServicesLoader();
    }
}
