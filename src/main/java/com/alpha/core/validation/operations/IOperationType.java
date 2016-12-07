package com.alpha.core.validation.operations;

import com.alpha.core.validation.ext.NumberVerification;

/**
 * Created by jzhou237 on 2016-12-07.
 */
public interface IOperationType {

    /**
     * get the OperationType by the type code.
     *
     * @param operationTypes
     * @param operation
     * @return IOperationType. if can't find it, then return Equal operation
     */
    static IOperationType getOperationType(IOperationType[] operationTypes, String operation) {
        IOperationType equalOperation = null;
        for (IOperationType operationType : operationTypes) {
            if (operationType.getType().equalsIgnoreCase(operation)) {
                return operationType;
            } else if (operationType.getType().equalsIgnoreCase("eq")) {
                equalOperation = operationType;
            }
        }
        return equalOperation == null ? NumberVerification.OperationType.EQUAL : equalOperation;
    }

    boolean compare(Object expectedValue, Object actualValue);

    String getMessageTemplate();

    String getType();

}
