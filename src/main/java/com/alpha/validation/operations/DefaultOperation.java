package com.alpha.validation.operations;

import com.sun.javafx.binding.StringFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jzhou237 on 2016-12-07.
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class DefaultOperation {

    protected String actualValue;

    protected IOperationType operationType;

    protected String expectValue;

    protected String operation;

    protected String formattedActualValue;

    protected String formattedExpectValue;

    public DefaultOperation(String actualValue, String expectValue, String operation) {
        this.actualValue = actualValue;
        this.expectValue = expectValue;
        this.operation = operation;
    }

    final public boolean operate() {
        operationType = getOperationType();
        formatValues();
        return operationType.compare(formattedExpectValue, formattedActualValue);
    }

    public String getMessage() {
        return StringFormatter.format(operationType.getMessageTemplate(), actualValue, expectValue).toString();
    }

    public abstract IOperationType getOperationType();

    public abstract void formatValues();


}
