package com.alpha.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Component
public class ErrorMessage implements View<FieldError> {

    private final MessageSource messageSource;
//    private final Model model;

    @Autowired
    public ErrorMessage(MessageSource messageSource) {
        this.messageSource = messageSource;
//        this.model = model;
    }

    @Override
    public void display(FieldError fieldError) {
//        model.addAttribute("error." + fieldError.getField(), messageSource.getMessage(fieldError, null));
    }
}
