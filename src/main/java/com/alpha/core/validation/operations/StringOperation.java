package com.alpha.core.validation.operations;

import com.alpha.core.validation.ext.NumberVerification;
import com.alpha.core.validation.ext.StringVerification;
import lombok.NoArgsConstructor;

/**
 * Created by jzhou237 on 2016-12-07.
 */
@NoArgsConstructor
public class StringOperation extends DefaultOperation {

    public StringOperation(String actualValue, String expectValue, String operation) {
        super(actualValue, expectValue, operation);
    }

    @Override
    public void formatValues() {
        this.formattedActualValue = this.actualValue;
        this.formattedExpectValue = this.expectValue;
    }

    @Override
    public IOperationType getOperationType() {
        NumberVerification.OperationType[] numberOperationTypes = NumberVerification.OperationType.values();
        StringVerification.OperationType[] stringOperationTypes = StringVerification.OperationType.values();
        IOperationType[] operationTypes = new IOperationType[numberOperationTypes.length + stringOperationTypes.length];
        System.arraycopy(numberOperationTypes, 0, operationTypes, 0, numberOperationTypes.length);
        System.arraycopy(stringOperationTypes, 0, operationTypes, stringOperationTypes.length, stringOperationTypes.length);
        return IOperationType.getOperationType(operationTypes, operation);
    }
}
