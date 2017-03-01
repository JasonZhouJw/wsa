package com.alpha.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.USER;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Controller
@RequestMapping(USER)
public class UserController {

    @Autowired
    @Qualifier("createView")
    private ModelAndView createView;

    public ModelAndView create() {
        return createView;
    }
}
