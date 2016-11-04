package com.alpha.web.model.common;

import com.alpha.web.utils.ResponseType;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-11-01.
 */
public class Response {

    transient private String modelView;

    transient private String errorModelView;

    private boolean isSuccess;

    private String code;

    private String message;

    private List<String> errorMessageList = new ArrayList<String>();

    private Response() {
    }

    private Response(String modelView, String errorModelView, ResponseType responseType, String message) {
        this.modelView = modelView;
        this.errorModelView = errorModelView;
        changeStatus(responseType, message);
    }

    public static Response init(String modelView) {
        return init(modelView, modelView);
    }

    public static Response init(String modelView, String errorModelView) {
        return init(modelView, errorModelView, ResponseType.SUCCESS);
    }

    public static Response init(String modelView, String errorModelView, String message) {
        return init(modelView, errorModelView, ResponseType.SUCCESS, message);
    }

    public static Response init(String modelView, String errorModelView, ResponseType responseType) {
        return init(modelView, errorModelView, ResponseType.SUCCESS, ResponseType.SUCCESS.getMessage());
    }

    public static Response init(String modelView, String errorModelView, ResponseType responseType, String message) {
        return new Response(modelView, errorModelView, responseType, message);
    }

    public static Response initError(String errorModelView, ResponseType responseType) {
        return init(null, errorModelView, responseType);
    }

    public static Response initError(String errorModelView, ResponseType responseType, String message) {
        return init(null, errorModelView, responseType, message);
    }

    public Response changeStatus(ResponseType responseType) {
        this.code = responseType.getCode();
        isSuccess = ResponseType.isSuccess(responseType);
        return this;
    }

    public Response changeStatus(ResponseType responseType, String message) {
        this.message = message;
        return changeStatus(responseType);
    }

    public Response addError(ResponseType responseType, String message) {
        this.errorMessageList.add(message);
        return this.changeStatus(responseType);
    }

    public Response addError(String message) {
        return addError(ResponseType.UNKNOWN_ERROR, message);
    }

    public Response addValidationError(String message) {
        return addError(ResponseType.VALIDATION_ERROR, message);
    }

    public Response changeView(String modelView) {
        this.modelView = modelView;
        return this;
    }

    public Response changeErrorView(String errorModelView) {
        this.errorModelView = errorModelView;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getView() {
        return StringUtils.isNotBlank(this.errorModelView) && !this.isSuccess ? this.errorModelView : this.modelView;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public Map<String, Object> getData() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("code", this.code);
        dataMap.put("isSuccess", this.isSuccess);
        dataMap.put("message", this.message);
        dataMap.put("errorMessageList", this.errorMessageList);
        return dataMap;
    }
}
