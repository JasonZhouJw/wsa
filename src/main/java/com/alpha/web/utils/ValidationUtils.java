package com.alpha.web.utils;

import com.alpha.web.exceptions.ValidationException;
import com.alpha.web.model.common.Response;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

    public static void validate(Object target, Response response) throws ValidationException {
        Set<ConstraintViolation<Object>> validateResult = validator.validate(target);
        if (CollectionUtils.isNotEmpty(validateResult)) {
            validateResult.forEach(item -> {
                response.addError(item.getMessage());
            });
            throw new ValidationException(response);
        }
    }

}
