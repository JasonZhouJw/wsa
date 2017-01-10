package com.aspects;

import com.annotation.NonClose;
import com.annotation.UserLogin;
import com.driver.UiDriver;
import com.pages.SignInPage;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by jzhou237 on 2017-01-10.
 */
@Slf4j
@Aspect
@Component
public class CucumberAspect {

    @Autowired
    private SignInPage signInPage;

    @Autowired
    private UiDriver uiDriver;

    @Around("execution(* com.steps.*.*())")
    public void aroundCucumber(ProceedingJoinPoint joinPoint) {
        Class[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            argTypes[i] = joinPoint.getArgs()[i].getClass();
        }
        Method targetMethod = null;
        try {
            targetMethod = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            log.error("Can not find the method [" + joinPoint.getSignature() + "] in class [" + joinPoint.getTarget().getClass().getName() + "].", e);
            uiDriver.close();
            return;
        }
        if (targetMethod != null) {
            UserLogin userLogin = targetMethod.getAnnotation(UserLogin.class);
            if (userLogin != null) {
                signInPage.signIn(userLogin.user(), userLogin.password());
            }
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
            } finally {
                Then then = targetMethod.getAnnotation(Then.class);
                NonClose nonClose = targetMethod.getAnnotation(NonClose.class);
                if (then != null && nonClose == null) {
                    uiDriver.close();
                }
            }
        }
    }
}
