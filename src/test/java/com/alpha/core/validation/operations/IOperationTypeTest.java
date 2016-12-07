package com.alpha.core.validation.operations;

import com.alpha.core.validation.ext.NumberVerification;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2016-12-07.
 */
public class IOperationTypeTest {
    @Test
    public void getOperationType() throws Exception {
        IOperationType actual = IOperationType.getOperationType(NumberVerification.OperationType.values(), "gt");
        assertNotNull(actual);
        assertEquals(actual.getType(), NumberVerification.OperationType.GREATER_THAN.getType());

        actual = IOperationType.getOperationType(NumberVerification.OperationType.values(), "not found");
        assertNotNull(actual);
        assertEquals(actual.getType(), NumberVerification.OperationType.EQUAL.getType());

        IOperationType[] operationTypes = new IOperationType[]{NumberVerification.OperationType.GREATER_THAN, NumberVerification.OperationType.GREATER_EQUAL};
        actual = IOperationType.getOperationType(NumberVerification.OperationType.values(), "not found");
        assertNotNull(actual);
        assertEquals(actual.getType(), NumberVerification.OperationType.EQUAL.getType());
    }

}