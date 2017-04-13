package com.alpha.common.interceptor;

import com.alpha.common.model.Message;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.alpha.common.controller.Urls.SEPARATOR;
import static com.alpha.common.view.BaseModelView.MESSAGE_PARAM;
import static com.alpha.common.view.PropertyResources.RESULT_MESSAGES_SHORT_NAME;

/**
 * Created by jzhou237 on 2017-04-11.
 */
public class ResultMessageInterceptor implements HandlerInterceptor {
    public static final String PREFIX = "result";

    private ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public ResultMessageInterceptor(ExposedResourceBundleMessageSource exposedResourceBundleMessageSource) {
        this.exposedResourceBundleMessageSource = exposedResourceBundleMessageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        List<Message> messageList = (List<Message>) modelAndView.getModelMap().get(MESSAGE_PARAM);
        if (messageList == null) {
            return;
        }
        Set<String> resultMessageKeys = allLabelMessageKeys(request);
        messageList.stream()
                .filter(message -> isResultMessageKeyForView(message.getMessage(), modelAndView.getViewName()))
                .filter(message -> resultMessageKeys.contains(message.getMessage()))
                .forEach(message -> replaceResultMessage(message, request));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void replaceResultMessage(Message message, HttpServletRequest request) {
        message.setMessage(exposedResourceBundleMessageSource.getMessageOverrided(message.getMessage(), null, request.getLocale()));
    }

    private boolean isResultMessageKeyForView(String key, String viewName) {
        return key != null && (isAllNameInKey(viewName, key) || key.startsWith(PREFIX));
    }

    private boolean isAllNameInKey(String viewName, String key) {
        return Stream.of(viewName.split(SEPARATOR)).allMatch(key::contains);
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(RESULT_MESSAGES_SHORT_NAME, request.getLocale());
    }
}
