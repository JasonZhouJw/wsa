package com.alpha.core.ws.validation.converter;

import java.util.function.Function;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public interface BaseConverter<T, R> extends Function<T, R> {

    boolean checkType(String clazzType);
}
