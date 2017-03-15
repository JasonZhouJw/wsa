package com.alpha.common.exceptions;

/**
 * Created by jzhou237 on 2017-03-15.
 */
public class DataNotFoundException extends DomainException {

    private static String message = "result.not.found.data";

    public DataNotFoundException() {
        super(message);
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
