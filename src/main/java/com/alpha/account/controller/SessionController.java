package com.alpha.account.controller;

import com.alpha.account.domain.AuthenticationResult;
import com.alpha.account.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Controller
@RequestMapping(ROOT)
public class SessionController {

    private final LoginView loginView;

    @Autowired
    public SessionController(LoginView loginView) {
        this.loginView = loginView;
    }

    @GetMapping(LOGIN)
    public ModelAndView login(@ModelAttribute AuthenticationResult authenticationResult) {
        loginView.display(authenticationResult);
        return loginView;
    }

    @PostMapping(LOGOUT)
    public ModelAndView logout(@ModelAttribute AuthenticationResult authenticationResult) {
        return new ModelAndView();
    }
}
