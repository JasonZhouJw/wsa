package com.alpha.core.ws.validation.enums;

import com.alpha.core.ws.validation.converter.BaseConverter;
import com.alpha.core.ws.validation.converter.NumberConverter;

/**
 * Created by jzhou237 on 9/14/2016.
 */
public enum ConverterType {

    NUMBER("NUMBER", new NumberConverter()),
    NONE("NONE", new BaseConverter<Object, String>() {
        @Override
        public boolean checkType(String clazzType) {
            return true;
        }

        public String apply(Object s) {
            return String.valueOf(s);
        }
    });

    private String type;

    private BaseConverter<Object, String> function;

    ConverterType(String type, BaseConverter<Object, String> function) {
        this.type = type;
        this.function = function;
    }

    public static ConverterType getConverterType(String clazzType) {
        ConverterType type = NONE;
        ConverterType[] types = ConverterType.values();
        for (ConverterType converterType : types) {
            if (!converterType.equals(NONE) && converterType.getFunction().checkType(clazzType)) {
                type = converterType;
                break;
            }
        }
        return type;
    }

    public String convert(Object sourceObj) {
        return this.function.apply(sourceObj);
    }

    public String getType() {
        return type;
    }

    public BaseConverter<Object, String> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "ConverterType{" +
                "type='" + type + '\'' +
                '}';
    }
}
