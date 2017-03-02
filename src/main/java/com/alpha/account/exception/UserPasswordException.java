package com.alpha.account.exception;

/**
 * Created by jzhou237 on 2017-03-02.
 */
public class UserPasswordException extends UserException {
    public UserPasswordException() {
    }

    public UserPasswordException(String message) {
        super(message);
    }

    public UserPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPasswordException(Throwable cause) {
        super(cause);
    }

    public UserPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
