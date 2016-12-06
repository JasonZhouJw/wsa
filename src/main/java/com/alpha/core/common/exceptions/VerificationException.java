package com.alpha.core.common.exceptions;

import com.alpha.core.common.utils.enums.Errors;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class VerificationException extends CommonException {

    public VerificationException() {
    }

    public VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationException(Throwable cause) {
        super(cause);
    }

    public VerificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VerificationException(Errors errors) {
        super(errors);
    }

    public VerificationException(Errors errors, String message) {
        super(errors, message);
    }

    public VerificationException(Errors errors, Throwable cause) {
        super(errors, cause);
    }

    public VerificationException(Errors errors, String message, Throwable cause) {
        super(errors, message, cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }
}
