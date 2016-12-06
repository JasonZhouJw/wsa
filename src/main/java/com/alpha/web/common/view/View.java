package com.alpha.web.common.view;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@FunctionalInterface
public interface View<T> {

    void display(T domainObject);
}
