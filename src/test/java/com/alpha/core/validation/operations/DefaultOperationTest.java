package com.alpha.core.validation.operations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by jzhou237 on 2016-12-07.
 */
public class DefaultOperationTest {
    @Test
    public void operate() throws Exception {
        DefaultOperation defaultOperation = new StringOperation();
        defaultOperation.setActualValue("123");
        defaultOperation.setExpectValue("234");
        defaultOperation.setOperation("eq");
        assertFalse(defaultOperation.operate());
        assertEquals("123", defaultOperation.getFormattedActualValue());
        assertEquals("234", defaultOperation.getFormattedExpectValue());

    }

}