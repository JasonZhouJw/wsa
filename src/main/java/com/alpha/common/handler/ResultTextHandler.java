package com.alpha.common.handler;

import com.alpha.common.interceptor.ExposedResourceBundleMessageSource;
import com.alpha.common.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.alpha.common.controller.Urls.SEPARATOR;
import static com.alpha.common.view.BaseModelView.MESSAGE_PARAM;
import static com.alpha.common.view.PropertyResources.RESULT_MESSAGES_SHORT_NAME;

/**
 * Created by jzhou237 on 2017-03-09.
 */
@Component
public class ResultTextHandler {

    public static final String PREFIX = "result";

    private ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public ResultTextHandler() {
        this.exposedResourceBundleMessageSource = new ExposedResourceBundleMessageSource();
    }

    public void addLabelMessage(HttpServletRequest request, ModelAndView modelAndView) {
        List<Message> messageList = (List<Message>) modelAndView.getModelMap().get(MESSAGE_PARAM);
        Set<String> resultMessageKeys = allLabelMessageKeys(request);
        messageList.stream()
                .filter(message -> isResultMessageKeyForView(message.getMessage(), modelAndView.getViewName()))
                .filter(message -> resultMessageKeys.contains(message.getMessage()))
                .forEach(message -> replaceResultMessage(message, request));
    }

    private void replaceResultMessage(Message message, HttpServletRequest request) {
        message.setMessage(exposedResourceBundleMessageSource.getMessageOverrided(message.getMessage(), null, request.getLocale()));
    }

    private boolean isResultMessageKeyForView(String key, String viewName) {
        return isAllNameInKey(viewName, key) || key.startsWith(PREFIX);
    }

    private boolean isAllNameInKey(String viewName, String key) {
        return Stream.of(viewName.split(SEPARATOR)).allMatch(key::contains);
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(RESULT_MESSAGES_SHORT_NAME, request.getLocale());
    }

}
