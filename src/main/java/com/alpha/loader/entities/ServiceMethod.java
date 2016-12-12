package com.alpha.loader.entities;

import edu.emory.mathcs.backport.java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-12.
 */
@Setter
@Getter
@NoArgsConstructor
public class ServiceMethod {

    private Method method;

    private List<Class> arguments = new ArrayList<>();

    private Class returnClass;

    public ServiceMethod(Method method) {
        this.method = method;
        analyseMethod();
    }

    private void analyseMethod() {
        this.returnClass = method.getReturnType();
        this.arguments = Arrays.asList(this.method.getParameterTypes());
    }
}
