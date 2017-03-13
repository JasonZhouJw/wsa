package com.alpha.account.view;

import com.alpha.account.entities.User;
import com.alpha.account.model.UserVo;
import com.alpha.common.view.BaseModelView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.ACCOUNT_UPDATE;

/**
 * Created by jzhou237 on 2017-03-03.
 */
@Component("accountUpdateView")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UpdateView extends BaseModelView {
    @Value("${label.success}")
    private String success;

    public UpdateView() {
        this.setViewName(ACCOUNT_UPDATE);
    }


    public void success() {
        this.addMessage(success);
    }

    public void setAccount(User user) {
        UserVo userVo = user != null ? user.toVo() : null;
        this.addObject("updatedUser", userVo);
    }
}
