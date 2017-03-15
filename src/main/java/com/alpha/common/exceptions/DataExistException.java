package com.alpha.common.exceptions;

/**
 * Created by jzhou237 on 2017-03-15.
 */
public class DataExistException extends DomainException {
    private static String message = "result.exist.data";

    public DataExistException() {
        super(message);
    }

    public DataExistException(String message) {
        super(message);
    }

    public DataExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataExistException(Throwable cause) {
        super(cause);
    }

    public DataExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
