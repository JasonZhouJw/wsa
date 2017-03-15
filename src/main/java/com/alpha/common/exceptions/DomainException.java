package com.alpha.common.exceptions;

/**
 * Created by jzhou237 on 2017-03-09.
 */
public class DomainException extends Exception {

    protected String message = "";

    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
        this.message = message;
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, false);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
