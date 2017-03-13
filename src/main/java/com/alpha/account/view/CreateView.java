package com.alpha.account.view;

import com.alpha.common.view.BaseModelView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.ACCOUNT_CREATE;
import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Component("accountCreateView")
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(LABEL_TEXT_FULL_NAME)
public class CreateView extends BaseModelView {

    @Value("${label.success}")
    private String success;

    public CreateView() {
        this.setViewName(ACCOUNT_CREATE);
    }

    public void success() {
        this.addMessage(success);
    }

}
