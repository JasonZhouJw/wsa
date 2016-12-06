package com.alpha.core.validation.ext;

import com.alpha.core.common.exceptions.CommonException;
import com.alpha.core.common.exceptions.VerificationException;
import com.alpha.core.common.utils.enums.Errors;
import com.alpha.core.validation.AbsVerification;
import com.alpha.core.validation.IOperation;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by jzhou237 on 9/20/2016.
 */
@Slf4j
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
                    log.error(e.getMessage(), e);
                }
            }
        });
        if (!target.operate()) {
            throw new VerificationException(Errors.VERIFY_FAIL, target.getMessage());
        }
    }

    public enum OperationType {

        EQUAL("EQUAL", "\"{0}\" value expect value is [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return StringUtils.equals(s, s2);
            }
        }),
        GREATER_THAN("GREATER_THAN", "\"{0}\" value expect value is greater than [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.compareTo(s2) > 0;
            }
        }),
        GREATER_EQUAL("GE", "\"{0}\" value expect value is equal or greater than [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.compareTo(s2) >= 0;
            }
        }),
        LESS_THAN("LT", "\"{0}\" value expect value is lower than [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.compareTo(s2) < 0;
            }
        }),
        LESS_EQUAL("LE", "\"{0}\" value expect value is equal or lower than [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.compareTo(s2) <= 0;
            }
        }),
        NOT_EQUAL("NE", "\"{0}\" value expect value is [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return !StringUtils.equals(s, s2);
            }
        }),
        LIKE("LK", "\"{0}\" value expect value is like [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.indexOf(s2) >= 0;
            }
        });

        private String type;

        private String messageTemplate;

        private BiFunction<String, String, Boolean> function;

        OperationType(String type, String messageTemplate, BiFunction<String, String, Boolean> function) {
            this.type = type;
            this.messageTemplate = messageTemplate;
            this.function = function;
        }

        public static OperationType getOperationType(String operation) {
            OperationType result = OperationType.EQUAL;
            for (OperationType operationType : OperationType.values()) {
                if (operationType.getType().equals(operation)) {
                    result = operationType;
                    break;
                }
            }
            return result;
        }

        public boolean compare(String expectedValue, String actualValue) {
            return this.function.apply(expectedValue, actualValue);
        }

        public String getType() {
            return type;
        }

        public String getMessageTemplate() {
            return messageTemplate;
        }

        public BiFunction<String, String, Boolean> getFunction() {
            return function;
        }
    }

    private class Operation implements IOperation {

        String actualValue;

        OperationType operationType;

        public boolean operate() {
            operationType = OperationType.getOperationType(operation);
            return operationType.compare(expect, actualValue);
        }

        public String getMessage() {
            return StringFormatter.format(operationType.getMessageTemplate(), field, expect, actualValue).toString();
        }
    }


}
