package com.alpha.core.ws.exception;

import com.alpha.core.ws.utils.enums.Errors;
import com.alpha.web.model.common.Response;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public class CommonException extends Exception {

    private Errors errors;

    private String message;

    private Response response;

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

    public CommonException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
