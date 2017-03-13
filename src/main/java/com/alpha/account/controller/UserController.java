package com.alpha.account.controller;

import com.alpha.account.domain.IUserDo;
import com.alpha.account.entities.User;
import com.alpha.account.exception.UserException;
import com.alpha.account.exception.UserNotFoundException;
import com.alpha.account.model.CreateUserVo;
import com.alpha.account.model.UserVo;
import com.alpha.account.view.CreateView;
import com.alpha.account.view.IndexView;
import com.alpha.account.view.UpdateView;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.page.PageView;
import com.alpha.common.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    @Qualifier("accountCreateView")
    private CreateView createView;

    @Autowired
    @Qualifier("accountUpdateView")
    private UpdateView updateView;

    @Autowired
    @Qualifier("accountIndexView")
    private IndexView indexView;

    @Autowired
    private PageView pageView;

    @Autowired
    private IUserDo user;

    @RequestMapping(ACCOUNT_INDEX)
    public ModelAndView toIndex() {
        this.user.findAll(new User(), pageView.create(), (userPage) -> {
            indexView.setAccounts(userPage.getContent());
            pageView.display(userPage.getTotalPages());
        });
        return indexView.Combine(pageView);
    }


    @RequestMapping(ACCOUNT_TO_CREATE)
    public ModelAndView toCreate() {
        return createView;
    }

    @PostMapping(ACCOUNT_CREATE)
    public CreateView create(CreateUserVo userVo, HttpServletResponse response, HttpServletRequest request) throws UserException, ValidationException {
        ValidationUtils.validate(userVo);
        User savedUser = user.create(userVo);
        createView.addObject("user", savedUser.toVo());
        createView.success();
        return createView;
    }

    @RequestMapping(ACCOUNT_TO_UPDATE + "/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Long id) {
        updateView.setAccount(this.user.findById(id));
        return updateView;
    }

    @PostMapping(ACCOUNT_UPDATE)
    public UpdateView update(UserVo userVo, HttpServletRequest request, HttpServletResponse response) throws UserNotFoundException {
        User updatedUser = user.update(userVo);
        updateView.setAccount(updatedUser);
        updateView.success();
        return updateView;
    }

}
