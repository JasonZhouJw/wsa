package com.alpha.web.aspects;

import com.alpha.core.ws.utils.ILog;
import com.alpha.web.exceptions.WebException;
import com.alpha.web.model.common.Response;
import com.alpha.web.utils.ResponseType;
import com.alpha.web.utils.WebConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jzhou237 on 2016-11-01.
 */
@Aspect
@Component
public class DefaultAspect implements ILog {

    @Around("execution(* com.alpha.web.controller.*Controller.*(..))")
    public String aroundMethod(ProceedingJoinPoint joinPoint) {
        Response response = Response.initError("common/Error", ResponseType.UNKNOWN_ERROR);
        try {
            String result = (String) joinPoint.proceed();
            response.changeStatus(ResponseType.SUCCESS).changeView(result);
        } catch (Throwable throwable) {
            LOGGER.error("Error Notice:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName(), throwable);
            if (throwable instanceof WebException) {
                WebException webException = (WebException) throwable;
                response = webException.getResponse();
            }
        }
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ModelMap) {
                ModelMap map = (ModelMap) arg;
                map.put(WebConstants.RESPONSE_KEY, response.getData());
                break;
            } else if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                request.setAttribute(WebConstants.RESPONSE_KEY, response.getData());
                break;
            }
        }
        return response.getView();
    }
}
