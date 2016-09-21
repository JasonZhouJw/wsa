package com.alpha.core.ws.validation.ext;

import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.exception.ValidationException;
import com.alpha.core.ws.utils.enums.Errors;
import com.alpha.core.ws.validation.AbsVerification;
import com.alpha.core.ws.validation.enums.OperationType;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

/**
 * Created by jzhou237 on 9/20/2016.
 */
public class StringVerification extends AbsVerification {

    private String expect;

    public StringVerification(String field, String operation) {
        super(field, operation);
    }

    public StringVerification(String field, String operation, String expect) {
        this(field, operation);
        this.expect = expect;
    }

    public static boolean isString(String function) {
        return StringUtils.equalsIgnoreCase("string", function);
    }

    @Override
    public void verify(Object actual) throws CommonException {
        Operation target = new Operation();
        this.getTargetField(actual, new BiConsumer<Field, Object>() {
            @Override
            public void accept(Field field, Object o) {
                field.setAccessible(true);
                try {
                    target.actualValue = (String) field.get(actual);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
        if (!target.operate()) {
            throw new ValidationException(Errors.VERIFY_FAIL, target.getMessage());
        }
    }

    private class Operation {

        String actualValue;

        OperationType operationType;

        boolean operate() {
            operationType = OperationType.getOperationType(operation);
            return operationType.compare(expect, actualValue);
        }

        String getMessage() {
            return StringFormatter.format(operationType.getMessageTemplate(), field, expect, actualValue).toString();
        }
    }
}
