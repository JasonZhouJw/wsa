package com.alpha.home.controller;

import com.alpha.common.controller.Urls;
import com.alpha.home.view.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Controller
@RequestMapping(Urls.ROOT)
public class HomeController {

    @Autowired
    private HomeView homeView;

    @GetMapping
    public ModelAndView index() {
        return homeView;
    }
}
