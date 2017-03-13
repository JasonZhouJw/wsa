package com.alpha.account.view;

import com.alpha.account.entities.User;
import com.alpha.account.model.UserVo;
import com.alpha.common.controller.Urls;
import com.alpha.common.view.BaseModelView;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by jzhou237 on 2017-03-13.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChangePasswordView extends BaseModelView {

    public ChangePasswordView() {
        this.setViewName(Urls.ACCOUNT_CHANGE_PASSWORD);
    }


    public void setAccount(User user) {
        UserVo userVo = user != null ? user.toVo() : null;
        this.addObject("updatedUser", userVo);
    }
}
