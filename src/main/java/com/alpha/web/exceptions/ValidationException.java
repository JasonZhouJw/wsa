package com.alpha.web.exceptions;

import com.alpha.web.model.common.Response;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class ValidationException extends WebException {

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

    public ValidationException(Response response) {
        super(response);
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }
}
