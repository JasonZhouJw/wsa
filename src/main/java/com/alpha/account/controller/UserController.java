package com.alpha.account.controller;

import com.alpha.account.domain.IUser;
import com.alpha.account.exception.UserException;
import com.alpha.account.model.UserVo;
import com.alpha.common.utils.Constants;
import com.alpha.common.view.BaseModelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.alpha.common.controller.Urls.CREATE_ACCOUNT;
import static com.alpha.common.controller.Urls.TO_CREATE_ACCOUNT;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Controller
public class UserController {

    @Autowired
    @Qualifier("createView")
    private BaseModelView createView;

    @Autowired
    private IUser user;


    @RequestMapping(TO_CREATE_ACCOUNT)
    public ModelAndView toCreate() {
        return createView;
    }

    @PostMapping(CREATE_ACCOUNT)
    public ModelAndView create(UserVo userVo, HttpServletResponse response, HttpServletRequest request) {
        try {
            com.alpha.account.entities.User savedUser = user.create(userVo);
            createView.addObject("userName", savedUser.getName());
            createView.addObject("userId", savedUser.getId());
            createView.success();
        } catch (UserException e) {
            createView.addMessage(e.getMessage(), Constants.WARNING);
        } catch (Exception e) {
            createView.addMessage(e.getMessage(), Constants.DANGER);
        }
        return createView;
    }
}
