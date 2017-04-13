package com.alpha.account.view;

import com.alpha.account.domain.AuthenticationResult;
import com.alpha.common.controller.Urls;
import com.alpha.common.view.View;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.utils.WebConstants.RESULT_MESSAGE_PROPERTIES;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGE_PROPERTIES)
@Setter
@Getter
public class LoginView extends ModelAndView implements View<AuthenticationResult> {

    @Value("${authentication.failed}")
    private String failedMessage;

    @Value("${authentication.logout")
    private String logoutMessage;

    public LoginView() {
        this.setViewName(Urls.LOGIN);
    }

    private void setMessageAndType(String message, String type) {
        addObject("MESSAGE", message);
        addObject("type", type);
    }

    @Override
    public void display(AuthenticationResult authenticationResult) {
        authenticationResult
                .error(() -> setMessageAndType(failedMessage, "danger"))
                .logout(() -> setMessageAndType(logoutMessage, "info"));
    }
}
