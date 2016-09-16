package com.alpha.core.ws.exception;

import com.alpha.core.ws.utils.enums.Errors;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class CommonException extends Exception {

    private Errors errors;

    private String message;

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException(Errors errors) {
        this.errors = errors;
        this.message = errors.getMessage();
    }

    public CommonException(Errors errors, String message) {
        this.errors = errors;
        this.message = message;
    }

    public CommonException(Errors errors, Throwable cause) {
        super(cause);
        this.errors = errors;
    }

    public CommonException(Errors errors, String message, Throwable cause) {
        super(cause);
        this.errors = errors;
        this.message = message;
    }
}
