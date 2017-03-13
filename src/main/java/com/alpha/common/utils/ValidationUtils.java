package com.alpha.common.utils;

import com.alpha.common.exceptions.ValidationException;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jzhou237 on 2016-11-02.
 */
public class ValidationUtils {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static void validate(Object target) throws ValidationException {
        Set<ConstraintViolation<Object>> validateResult = validator.validate(target);
        if (CollectionUtils.isNotEmpty(validateResult)) {
            List<String> messages = new ArrayList<>();
            validateResult.forEach(item -> {
                messages.add(item.getMessage());
            });
            throw new ValidationException(messages);
        }
    }

}
