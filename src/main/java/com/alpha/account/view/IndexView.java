package com.alpha.account.view;

import com.alpha.account.entities.User;
import com.alpha.account.model.UserVo;
import com.alpha.common.view.BaseModelView;
import com.alpha.common.view.ModelAndViewCombiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.common.controller.Urls.ACCOUNT_INDEX;
import static com.alpha.common.controller.Urls.ACCOUNT_TO_UPDATE;
import static com.alpha.common.view.PropertyResources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by jzhou237 on 2017-03-03.
 */
@Component("accountIndexView")
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class IndexView extends BaseModelView implements ModelAndViewCombiner {

    public IndexView(@Value("${list.empty}") String noDataMessage) {
        addObject("noDataMessage", noDataMessage);
        addObject("updateUrl", ACCOUNT_TO_UPDATE);
        this.setViewName(ACCOUNT_INDEX);
    }

    public void setAccounts(List<User> userList) {
        List<UserVo> userVoList = new ArrayList<>();
        userList.forEach(user -> userVoList.add(user.toVo()));
        addObject("userList", userVoList);
    }

    @Override
    public ModelAndView original() {
        return this;
    }
}
