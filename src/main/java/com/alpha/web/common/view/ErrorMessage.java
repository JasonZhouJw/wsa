package com.alpha.web.common.view;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Component
public class ErrorMessage implements View<FieldError> {

    @Override
    public void display(FieldError domainObject) {

    }
}
