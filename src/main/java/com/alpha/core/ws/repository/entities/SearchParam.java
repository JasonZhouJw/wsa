package com.alpha.core.ws.repository.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-11-04.
 */
public class SearchParam {

    List<SearchParam> parameters = new ArrayList<SearchParam>();

    private String field;

    private Operation operation;

    private List<Object> values = new ArrayList<Object>();

    public SearchParam() {

    }

    public SearchParam(String field, Object... values) {
        this(field, Operation.EQUAL, values);
    }

    public SearchParam(String field, Operation operation, Object... values) {
        this.field = field;
        this.operation = operation;
        if (values.length > 0) {
            for (Object obj : values) {
                this.values.add(obj);
            }
        } else if (!Operation.isNotNeedValue(operation)) {
            operation = Operation.IS_NULL;
        }
    }

    public List<SearchParam> getParameters() {
        return parameters;
    }

    public void setParameters(List<SearchParam> parameters) {
        this.parameters = parameters;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public enum Operation {

        EQUAL("EQ", " = "),
        GREATER_THAN("GT", " > "),
        IS_NULL("null", " is null ");

        private String alias;

        private String operate;

        Operation(String alias, String operate) {
            this.alias = alias;
            this.operate = operate;
        }

        public static boolean isNotNeedValue(Operation operation) {
            return operation.getAlias().equals(IS_NULL.getAlias());
        }

        public String getAlias() {
            return alias;
        }

        public String getOperate() {
            return operate;
        }
    }
}
