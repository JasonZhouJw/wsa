package com.alpha.core.ws.utils.enums;

import org.apache.commons.lang.StringUtils;

import java.util.function.BiFunction;

/**
 * Created by jzhou237 on 9/12/2016.
 */
public enum OperationType {

    EQUAL("EQ", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return StringUtils.equals(s, s2);
        }
    }),
    GREATER_THAN("GT", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return s.compareTo(s2)>0;
        }
    }),
    GREATER_EQUAL("GE", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return s.compareTo(s2)>=0;
        }
    }),
    LESS_THAN("LT", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return s.compareTo(s2)<0;
        }
    }),
    LESS_EQUAL("LE", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return s.compareTo(s2)<=0;
        }
    }),
    NOT_EQUAL("NE", new BiFunction<String, String, Boolean>() {
        @Override
        public Boolean apply(String s, String s2) {
            return !StringUtils.equals(s, s2);
        }
    });

    private String type;

    private BiFunction<String, String, Boolean> function;

    private OperationType(String type, BiFunction<String, String, Boolean> function) {
        this.type = type;
        this.function = function;
    }

    public String toString() {
        return this.type;
    }

    public boolean compare(String expectedValue,String actualValue){
        return this.function.apply(expectedValue, actualValue);
    }
}
