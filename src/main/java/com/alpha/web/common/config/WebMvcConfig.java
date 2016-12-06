package com.alpha.web.common.config;

import com.alpha.web.common.interceptor.ExposedResourceBundleMessageSource;
import com.alpha.web.common.interceptor.LabelTextInterceptor;
import com.alpha.web.common.view.ErrorMessage;
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
//        registry.addInterceptor(new AuthenticationInterceptor());
//        registry.addInterceptor(new LayoutNavigationInterceptor());
//        registry.addInterceptor(new ErrorMessageInterceptor(errorMessage));
        // TODO: 2016-12-05 modify the label interceptor
        registry.addInterceptor(new LabelTextInterceptor(new ExposedResourceBundleMessageSource()));
        super.addInterceptors(registry);
    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        super.addFormatters(registry);
////        registry.addFormatterForFieldAnnotation(new MonthFormatAnnotationFormatterFactory());
//    }
}
