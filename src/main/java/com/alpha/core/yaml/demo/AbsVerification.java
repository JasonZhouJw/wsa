package com.alpha.core.yaml.demo;

import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.enums.Errors;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

/**
 * Created by jzhou237 on 9/20/2016.
 */
public abstract class AbsVerification implements ILog {

    protected String field;

    protected String operation;

    public AbsVerification(String field, String operation) {
        this.field = field;
        this.operation = operation;
    }

    public abstract void verify(Object actual) throws CommonException;


    protected void getTargetField(Object targetObj, BiConsumer<Field, Object> consumer) throws CommonException {
        this.getTargetField(this.field, targetObj, consumer);
    }

    protected void getTargetField(String field, Object targetObj, BiConsumer<Field, Object> consumer) throws CommonException {
        if (StringUtils.isBlank(field) || targetObj == null) {
            throw new CommonException(Errors.NULL_POINT);
        }
        Field targetField = null;
        if (field.indexOf(".") > 0) {
            String childField = field.substring(0, field.indexOf("."));
            try {
                getTargetField(field.substring(field.indexOf(".") + 1), targetObj.getClass().getField(childField), consumer);
            } catch (NoSuchFieldException e) {
                LOGGER.error(e.getMessage(), e);
                throw new CommonException(Errors.FIELD_NOT_FOUND);
            }
        } else {
            try {
                targetField = targetObj.getClass().getField(this.field);
            } catch (NoSuchFieldException e) {
                LOGGER.error(e.getMessage(), e);
                throw new CommonException(Errors.FIELD_NOT_FOUND);
            }
        }
        if (targetField != null) {
            consumer.accept(targetField, targetObj);
        } else {
            throw new CommonException(Errors.UNKNOWN);
        }
    }
}
