package com.alpha.validation.operations;

import com.alpha.common.utils.Constants;
import com.alpha.validation.ext.StringVerification;
import lombok.NoArgsConstructor;

/**
 * Created by jzhou237 on 2016-12-07.
 */
@NoArgsConstructor
public class NumberOperation extends DefaultOperation {

    public NumberOperation(String actualValue, String expectValue, String operation) {
        super(actualValue, expectValue, operation);
    }

    @Override
    public IOperationType getOperationType() {
        return IOperationType.getOperationType(StringVerification.OperationType.values(), operation);
    }

    @Override
    public void formatValues() {
        String[] targetStrs = actualValue.split(Constants.SEPARATE_DOT);
        String[] expectStrs = expectValue.split(Constants.SEPARATE_DOT);
        if (targetStrs.length == 2 && expectStrs.length == 2) {
            int integerLength = Integer.max(targetStrs[0].length(), expectStrs[0].length());
            int decimalsLength = Integer.max(targetStrs[1].length(), expectStrs[1].length());
            actualValue = String.format("%" + integerLength + "s", targetStrs[0]) + Constants.DOT + String.format("%" + decimalsLength + "s", targetStrs[1]);
            expectValue = String.format("%" + integerLength + "s", expectStrs[0]) + Constants.DOT + String.format("%" + decimalsLength + "s", expectStrs[1]);
        } else if (targetStrs.length == 2) {
            int integerLength = Integer.max(targetStrs[0].length(), expectValue.length());
            actualValue = String.format("%" + integerLength + "s", targetStrs[0]) + Constants.DOT + targetStrs[1];
            expectValue = String.format("%" + integerLength + "s", expectValue);
        } else if (expectStrs.length == 2) {
            int integerLength = Integer.max(expectStrs[0].length(), actualValue.length());
            actualValue = String.format("%" + integerLength + "s", actualValue);
            expectValue = String.format("%" + integerLength + "s", expectStrs[0]) + Constants.DOT + expectStrs[1];
        } else {
            int integerLength = Integer.max(actualValue.length(), expectValue.length());
            actualValue = String.format("%" + integerLength + "s", actualValue);
            expectValue = String.format("%" + integerLength + "s", expectValue);
        }
    }
}
