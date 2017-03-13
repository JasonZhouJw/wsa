package com.alpha.common.handler;

import com.alpha.common.interceptor.ExposedResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Stream;

import static com.alpha.common.controller.Urls.SEPARATOR;
import static com.alpha.common.view.PropertyResources.LABEL_TEXT_SHORT_NAME;

/**
 * Created by jzhou237 on 2017-03-09.
 */
@Component
public class LabelTextHandler {

    public static final String PREFIX = "label";

    private ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public LabelTextHandler() {
        this.exposedResourceBundleMessageSource = new ExposedResourceBundleMessageSource();
    }

    public void addLabelMessage(HttpServletRequest request, ModelAndView modelAndView) {
        allLabelMessageKeys(request).stream()
                .filter(key -> isLabelMessageKeyForView(key, modelAndView.getViewName()))
                .forEach(key -> addLabelMessage(key, modelAndView, request));
    }

    private ModelAndView addLabelMessage(String key, ModelAndView modelAndView, HttpServletRequest request) {
        return modelAndView.addObject(labelMessageCodeForView(key), exposedResourceBundleMessageSource.getMessageOverrided(key, null, request.getLocale()));
    }

    private String labelMessageCodeForView(String key) {
        return key.substring(key.indexOf(PREFIX));
    }

    private boolean isLabelMessageKeyForView(String key, String viewName) {
        return isAllNameInKey(viewName, key) || key.startsWith(PREFIX);
    }

    private boolean isAllNameInKey(String viewName, String key) {
        return Stream.of(viewName.split(SEPARATOR)).allMatch(key::contains);
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(LABEL_TEXT_SHORT_NAME, request.getLocale());
    }
}
