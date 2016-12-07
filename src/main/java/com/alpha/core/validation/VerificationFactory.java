package com.alpha.core.validation;

import com.alpha.core.validation.ext.NumberVerification;
import com.alpha.core.validation.ext.StringVerification;

import java.util.Map;

/**
 * Created by jzhou237 on 9/20/2016.
 */
public class VerificationFactory {

    public static final String FUNCTION = "function";

    public static final String FIELD = "field";

    public static final String OPERATION = "operation";

    public static final String EXPECT = "expect";

    public static AbsVerification getVerification(Map<String, String> configMap) {
        String function = configMap.get(FUNCTION);
        if (StringVerification.isString(function)) {
            return new StringVerification(configMap.get(FIELD), configMap.get(OPERATION), configMap.get(EXPECT));
        } else if (NumberVerification.isNumber(function)) {
            return new NumberVerification(configMap.get(FIELD), configMap.get(OPERATION), configMap.get(EXPECT));
        }
        return null;
    }
}
