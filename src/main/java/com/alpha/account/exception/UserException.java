package com.alpha.account.exception;

import com.alpha.common.exceptions.DomainException;

/**
 * Created by jzhou237 on 2017-03-02.
 */
public class UserException extends DomainException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
