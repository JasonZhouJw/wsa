package com.alpha.jobs.exception;

import com.alpha.common.exceptions.DomainException;

/**
 * Created by jzhou237 on 2017-03-15.
 */
public class JobClassNotFoundException extends DomainException {
    public JobClassNotFoundException() {
    }

    public JobClassNotFoundException(String message) {
        super(message);
    }

    public JobClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobClassNotFoundException(Throwable cause) {
        super(cause);
    }

    public JobClassNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
