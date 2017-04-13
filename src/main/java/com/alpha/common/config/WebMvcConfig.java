package com.alpha.common.config;

import com.alpha.common.interceptor.*;
import com.alpha.common.view.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ErrorMessage errorMessage;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JsonResponseInterceptor());
        registry.addInterceptor(new ResultMessageInterceptor(new ExposedResourceBundleMessageSource()));
        registry.addInterceptor(new AuthenticationInterceptor());
        registry.addInterceptor(new LayoutNavigationInterceptor());
        registry.addInterceptor(new LabelTextInterceptor(new ExposedResourceBundleMessageSource()));
        super.addInterceptors(registry);
    }


//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);
////        registry.addFormatterForFieldAnnotation(new MonthFormatAnnotationFormatterFactory());
//    }
}
