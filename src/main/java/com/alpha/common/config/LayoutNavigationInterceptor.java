package com.alpha.common.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.alpha.common.controller.Urls.LOGOUT;
import static com.alpha.common.controller.Urls.ROOT;

public class LayoutNavigationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        modelAndView.addObject("monthlyBudgetsUrl", MONTHLYBUDGETS_ADD);
//        modelAndView.addObject("transactionsUrl", TRANSACTIONS);
//        modelAndView.addObject("accountsUrl", ACCOUNTS);
        modelAndView.addObject("logoutUrl", LOGOUT);
        modelAndView.addObject("rootUrl", ROOT);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //no implementation needed
    }
}
