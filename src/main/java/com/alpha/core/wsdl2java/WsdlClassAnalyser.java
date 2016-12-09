package com.alpha.core.wsdl2java;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-08.
 */
public class WsdlClassAnalyser {

    private Map<String, Class> unassignedRequest = new HashMap<>();

    public WsdlClassAnalyser analyse(Class clazz) {
        boolean analysed = analyseRequest(clazz) || analyseResponse(clazz) || analyseServices(clazz) || analyseServiceFactory(clazz);
        return this;
    }

    private boolean analyseServiceFactory(Class clazz) {
        boolean result = false;
        Class superClazz = clazz.getSuperclass();
        if (superClazz.getName().equals("javax.xml.ws.Service")) {
            System.out.print(superClazz);
        }
        return result;
    }

    private boolean analyseServices(Class clazz) {
        boolean result = false;
        if (clazz.isInterface()) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {

            }
        }
        return result;
    }

    private boolean analyseRequest(Class clazz) {
        boolean result = false;
        return result;
    }

    private boolean analyseResponse(Class clazz) {
        boolean result = false;
        return result;
    }

    private class Services {
        String clazzName;
        String servicesName;
        List<String> requestName = new ArrayList<>();
        String returnName;
    }

}
