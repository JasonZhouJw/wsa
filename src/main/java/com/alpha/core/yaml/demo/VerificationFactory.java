package com.alpha.core.yaml.demo;

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
        } else if (IntVerification.isInteger(function)) {
            return new IntVerification(configMap.get(FIELD), configMap.get(OPERATION), configMap.get(EXPECT));
        }
        return null;
    }
}
