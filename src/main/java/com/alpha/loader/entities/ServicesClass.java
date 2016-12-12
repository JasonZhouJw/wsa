package com.alpha.loader.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.el.MethodNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class ServicesClass {

    private Class clazz;

    private Method serviceMethod;

    private Class interfaceClazz;

    private Map<String, ServiceMethod> servicesMethods = new HashMap<>();

    public ServicesClass(Class clazz) {
        this.clazz = clazz;
        findServicesMethod();
    }

    private void analyseServiceInterface(Class interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
        Method[] methods = this.interfaceClazz.getMethods();
        for (Method method : methods) {
            servicesMethods.put(method.getName(), new ServiceMethod(method));
        }
    }

    private void findServicesMethod() {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0 && method.getName().startsWith("get")) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.getClass().getName().equals("javax.xml.ws.WebEndpoint")) {
                        this.serviceMethod = method;
                        this.analyseServiceInterface(this.serviceMethod.getReturnType());
                        return;
                    }
                }
            }
        }
        if (this.serviceMethod == null) {
            throw new MethodNotFoundException("Can not find the method which return type is " + interfaceClazz.getName());
        }
    }

    public Object getServicesFactory() throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public Object getServicesObject() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Object servicesFactory = this.getServicesFactory();
        return this.serviceMethod.invoke(servicesFactory);
    }

    public String getServiceName() {
        return this.interfaceClazz.getName();
    }
}
