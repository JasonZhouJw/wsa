package com.alpha.validation.ext;

import com.alpha.common.enums.Errors;
import com.alpha.common.exceptions.CommonException;
import com.alpha.common.exceptions.VerificationException;
import com.alpha.validation.AbsVerification;
import com.alpha.validation.operations.DefaultOperation;
import com.alpha.validation.operations.IOperationType;
import com.alpha.validation.operations.StringOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by jzhou237 on 9/20/2016.
 */
@Slf4j
public class StringVerification extends AbsVerification {

    private String expect;

    public StringVerification(String field, String operation) {
        super(field, operation);
    }

    public StringVerification(String field, String operation, String expect) {
        this(field, operation);
        this.expect = expect;
    }

    public static boolean isString(String function) {
        return StringUtils.equalsIgnoreCase("string", function);
    }

    @Override
    public void verify(Object actual) throws CommonException {
        DefaultOperation target = new StringOperation();
        this.getTargetField(actual, new BiConsumer<Field, Object>() {
            @Override
            public void accept(Field field, Object o) {
                field.setAccessible(true);
                try {
                    target.setActualValue((String) field.get(actual));
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
            }
        });
        if (!target.operate()) {
            throw new VerificationException(Errors.VERIFY_FAIL, target.getMessage());
        }
    }

    @Getter
    public enum OperationType implements IOperationType {

        LIKE("LK", "\"{0}\" value expect value is like [{1}], but actual value is [{2}]", new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) {
                return s.indexOf(s2) >= 0;
            }
        });

        private String type;

        private String messageTemplate;

        private BiFunction<String, String, Boolean> function;

        OperationType(String type, String messageTemplate, BiFunction<String, String, Boolean> function) {
            this.type = type;
            this.messageTemplate = messageTemplate;
            this.function = function;
        }

        public boolean compare(Object expectedValue, Object actualValue) {
            return this.function.apply((String) expectedValue, (String) actualValue);
        }

    }

}
