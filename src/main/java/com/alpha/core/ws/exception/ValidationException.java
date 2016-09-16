package com.alpha.core.ws.exception;

import com.alpha.core.ws.utils.enums.Errors;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class ValidationException extends CommonException {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException(Errors errors) {
        super(errors);
    }

    public ValidationException(Errors errors, String message) {
        super(errors, message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }

    public ValidationException(Errors errors, Throwable cause) {
        super(errors, cause);
    }

    public ValidationException(Errors errors, String message, Throwable cause) {
        super(errors, message, cause);
    }
}
