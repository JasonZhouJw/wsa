package com.alpha.common.interceptor;

import com.alpha.common.model.ViewElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static com.alpha.common.view.PropertyResources.LABEL_TEXT_SHORT_NAME;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Slf4j
public class LabelTextInterceptor implements HandlerInterceptor {
    public static final String PREFIX = "label";
    public static final String VIEW_NAME_DELIMITER = "/";
    private final ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public LabelTextInterceptor(ExposedResourceBundleMessageSource exposedResourceBundleMessageSource) {
        this.exposedResourceBundleMessageSource = exposedResourceBundleMessageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Set<String> labelKeySet = allLabelMessageKeys(request);
        labelKeySet.stream()
                .filter(key -> isLabelMessageKeyForView(key, modelAndView.getViewName()))
                .forEach(key -> addLabelMessage(key, modelAndView, request));
        replaceLabelInDataModel(labelKeySet, modelAndView, request);
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
        return Stream.of(viewName.split(VIEW_NAME_DELIMITER)).allMatch(key::contains);
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(LABEL_TEXT_SHORT_NAME, request.getLocale());
    }

    private void replaceLabelInDataModel(Set<String> labelKeys, ModelAndView view, HttpServletRequest request) {
        view.getModelMap().forEach((key, data) -> {
            if (data instanceof Collection) {
                ((Collection) data).forEach((element) -> {
                    this.replaceLabel(labelKeys, element, request);
                });
            } else if (data.getClass().isArray()) {
                Object[] targetArray = (Object[]) data;
                for (int i = 0; i < targetArray.length; i++) {
                    this.replaceLabel(labelKeys, targetArray[i], request);
                }
            } else {
                this.replaceLabel(labelKeys, data, request);
            }
        });
    }

    private void replaceLabel(Set<String> labelKeys, Object target, HttpServletRequest request) {
        if (target instanceof ViewElement && labelKeys.contains(((ViewElement) target).getLabel())) {
            ((ViewElement) target).setLabel(exposedResourceBundleMessageSource.getMessageOverrided(((ViewElement) target).getLabel(), null, request.getLocale()));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
