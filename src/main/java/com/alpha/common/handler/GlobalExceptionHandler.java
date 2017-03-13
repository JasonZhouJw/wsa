package com.alpha.common.handler;

import com.alpha.common.exceptions.DomainException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.view.BaseModelView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.alpha.common.utils.SpringUtil.getBean;

/**
 * Created by jzhou237 on 2017-03-09.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private LabelTextHandler labelTextHandler;

    @Autowired
    private ResultTextHandler resultTextHandler;

//    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(value = DomainException.class)
    public ModelAndView domainExceptionHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception exception) throws Exception {
        BaseModelView errorView = null;
        HandlerMethod handlerMethod = handler;
        Class methodReturnType = handlerMethod.getMethod().getReturnType();
        DomainException domainException = (DomainException) exception;
        if (methodReturnType.getSuperclass().getName().equals(BaseModelView.class.getName())) {
            try {
                errorView = (BaseModelView) getBean(methodReturnType);
                errorView.addDanger(domainException.getMessage());
            } catch (NoUniqueBeanDefinitionException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            } catch (NoSuchBeanDefinitionException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            } catch (BeansException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            }
        } else {
            log.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage());
        }
        labelTextHandler.addLabelMessage(request, errorView);
        resultTextHandler.addLabelMessage(request, errorView);
        return errorView;
    }

    @ExceptionHandler(value = ValidationException.class)
    public ModelAndView validationExceptionHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception exception) throws Exception {
        HandlerMethod handlerMethod = handler;
        Class methodReturnType = handlerMethod.getMethod().getReturnType();
        ValidationException validationException = (ValidationException) exception;
        if (methodReturnType.getSuperclass().getName().equals(BaseModelView.class.getName())) {
            try {
                BaseModelView errorView = (BaseModelView) getBean(methodReturnType);
                validationException.getMessages().forEach(message -> {
                    errorView.addDanger(message);
                });
                labelTextHandler.addLabelMessage(request, errorView);
                resultTextHandler.addLabelMessage(request, errorView);
                return errorView;
            } catch (NoUniqueBeanDefinitionException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            } catch (NoSuchBeanDefinitionException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            } catch (BeansException e) {
                log.error(e.getMessage(), e);
                throw new Exception(e);
            }
        }
        log.error(exception.getMessage(), exception);
        throw new Exception(exception.getMessage());
    }
}
