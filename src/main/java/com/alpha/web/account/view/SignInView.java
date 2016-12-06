package com.alpha.web.account.view;

import com.alpha.web.account.domain.AuthenticationResult;
import com.alpha.web.common.view.View;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.web.common.controller.Urls.SIGN_IN;
import static com.alpha.web.common.utils.WebConstants.RESULT_MESSAGE_PROPERTIES;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGE_PROPERTIES)
@Setter
@Getter
public class SignInView extends ModelAndView implements View<AuthenticationResult> {

    @Value("${authentication.failed}")
    private String failedMessage;

    @Value("${authentication.logout")
    private String logoutMessage;

    public SignInView() {
        this.setViewName(SIGN_IN);
    }

    private void setMessageAndType(String message, String type) {
        addObject("message", message);
        addObject("type", type);
    }

    @Override
    public void display(AuthenticationResult authenticationResult) {
        authenticationResult
                .error(() -> setMessageAndType(failedMessage, "danger"))
                .logout(() -> setMessageAndType(logoutMessage, "info"));
    }
}
