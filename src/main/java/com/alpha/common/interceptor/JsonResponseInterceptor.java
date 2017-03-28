package com.alpha.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2017-03-27.
 */
@Slf4j
public class JsonResponseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, Object> filterData = new HashMap<String, Object>();
        // TODO: 2017-03-28 all response data exclude BindingResult will be convert to JSON and store in ModelAndView.
        modelAndView.getModelMap().forEach((key, value) -> {
            if (!key.startsWith("org.springframework.validation.BindingResult")) {
                filterData.put(key, value);
            }
        });
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            modelAndView.addObject("jsonData", objectMapper.writeValueAsString(filterData));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
