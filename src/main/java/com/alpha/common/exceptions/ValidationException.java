package com.alpha.common.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 9/14/2016.
 */
@Getter
public class ValidationException extends WebException {

    private List<String> messages = new ArrayList<>();

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

    public ValidationException(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }

    public String getAllMessage() {
        StringBuffer stringBuffer = new StringBuffer();
        this.messages.forEach(message -> {
            stringBuffer.append(message);
        });
        return stringBuffer.toString();
    }
}
