package com.alpha.account.controller;

import com.alpha.account.domain.AuthenticationResult;
import com.alpha.account.view.SignInView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.ROOT;
import static com.alpha.common.controller.Urls.SIGN_IN;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Controller
@RequestMapping(ROOT)
public class SessionController {

    private final SignInView signInView;

    @Autowired
    public SessionController(SignInView signInView) {
        this.signInView = signInView;
    }

    @GetMapping(SIGN_IN)
    public ModelAndView signIn(@ModelAttribute AuthenticationResult authenticationResult) {
        signInView.display(authenticationResult);
        return signInView;
    }
}
