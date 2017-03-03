package com.alpha.account.controller;

import com.alpha.account.domain.IUser;
import com.alpha.account.exception.UserException;
import com.alpha.account.model.UserVo;
import com.alpha.account.view.CreateView;
import com.alpha.account.view.IndexView;
import com.alpha.account.view.UpdateView;
import com.alpha.common.page.PageView;
import com.alpha.common.utils.Constants;
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
    private IUser user;

    @RequestMapping(ACCOUNT_INDEX)
    public ModelAndView toIndex() {
        this.user.findAll(pageView.create(), (userPage) -> {
            indexView.setAccounts(userPage.getContent());
            pageView.display(userPage.getTotalPages());
        });
        return indexView.Combine(pageView);
    }


    @RequestMapping(TO_CREATE_ACCOUNT)
    public ModelAndView toCreate() {
        return createView;
    }

    @PostMapping(CREATE_ACCOUNT)
    public ModelAndView create(UserVo userVo, HttpServletResponse response, HttpServletRequest request) {
        try {
            com.alpha.account.entities.User savedUser = user.create(userVo);
            createView.addObject("user", savedUser.toVo());
            createView.success();
        } catch (UserException e) {
            log.warn(e.getMessage(), e);
            createView.addMessage(e.getMessage(), Constants.WARNING);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            createView.addMessage(e.getMessage(), Constants.DANGER);
        }
        return createView;
    }

    @RequestMapping(TO_UPDATE_ACCOUNT + "/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Long id) {
        updateView.setAccount(this.user.findById(id));
        return updateView;
    }
}
