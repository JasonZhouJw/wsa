package com.alpha.common.interceptor;

import com.alpha.common.controller.Urls;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jzhou237 on 2016-12-05.
 */
public class LayoutNavigationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        modelAndView.addObject("homeUrl", Urls.HOME);
        modelAndView.addObject("caseGroupUrl", Urls.CASE_GROUP_INDEX);
        modelAndView.addObject("testCaseUrl", Urls.TEST_CASE_INDEX);
        modelAndView.addObject("accountUrl", Urls.ACCOUNT_INDEX);
        modelAndView.addObject("verifyResultUrl", Urls.VERIFY_RESULT_INDEX);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //no implementation needed
    }
}
