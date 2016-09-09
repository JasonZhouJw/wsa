package com.alpha.core.ws.utils;

import java.lang.reflect.Method;

public class ReflectUtil {

    @SuppressWarnings("rawtypes")
    public static Method getTargetMethod(Class clazz, String targetMethodName) {
        Method targetMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(targetMethodName)) {
                targetMethod = method;
                break;
            }
        }
        return targetMethod;
    }
}
