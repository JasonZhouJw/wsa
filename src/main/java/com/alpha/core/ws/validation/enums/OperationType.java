package com.alpha.core.ws.validation.enums;

import org.apache.commons.lang.StringUtils;

import java.util.function.BiFunction;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public enum OperationType {

    EQUAL("EQ", "\"{0}\" value expect value is [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return StringUtils.equals(s, s2);
        }
    }),
    GREATER_THAN("GT", "\"{0}\" value expect value is greater than [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
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

    public String toString() {
        return this.type;
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
