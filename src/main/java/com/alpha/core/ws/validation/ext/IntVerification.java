package com.alpha.core.ws.validation.ext;

import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.validation.AbsVerification;
import org.apache.commons.lang.StringUtils;

/**
 * Created by jzhou237 on 9/20/2016.
 */
public class IntVerification extends AbsVerification {

    private Integer expect;

    public IntVerification(String field, String operation) {
        super(field, operation);
    }

    public IntVerification(String field, String operation, String expect) {
        this(field, operation);
        this.expect = Integer.valueOf(expect);
    }

    public static boolean isInteger(String function) {
        return StringUtils.equalsIgnoreCase("int", function) || StringUtils.equalsIgnoreCase("integer", function);
    }

    @Override
    public void verify(Object actual) throws CommonException {

    }
}
