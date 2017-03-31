package com.alpha.common.utils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static void setLongNotNull(Supplier<Long> supplier, Consumer<Long> consumer) {
        Long value = supplier.get();
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static void setStringNotNull(Supplier<String> supplier, Consumer<String> consumer) {
        String value = supplier.get();
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static void setBooleanNotNull(Supplier<Boolean> supplier, Consumer<Boolean> consumer) {
        Boolean value = supplier.get();
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static void setDateNotNull(Supplier<Date> supplier, Consumer<Date> consumer) {
        Date value = supplier.get();
        if (value != null) {
            consumer.accept(value);
        }
    }

}
