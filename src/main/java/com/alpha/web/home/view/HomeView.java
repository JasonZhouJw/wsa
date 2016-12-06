package com.alpha.web.home.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.web.common.controller.Urls.HOME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class HomeView extends ModelAndView {

    public HomeView() {
        this.setViewName(HOME);
    }
}
