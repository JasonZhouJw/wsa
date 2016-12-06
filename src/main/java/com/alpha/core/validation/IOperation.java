package com.alpha.core.validation;

/**
 * Created by jzhou237 on 2016-09-21.
 */
public interface IOperation {

    /**
     * verify the value
     *
     * @return
     */
    boolean operate();

    /**
     * get the error message
     *
     * @return
     */
    String getMessage();
}
