package com.alpha.common.exceptions;

/**
 * Created by jzhou237 on 2017-03-09.
 */
public class DomainException extends Exception {
    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, false);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
