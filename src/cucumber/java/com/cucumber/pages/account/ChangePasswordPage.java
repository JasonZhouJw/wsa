package com.cucumber.pages.account;

import com.cucumber.driver.UiDriver;
import com.cucumber.driver.UiElement;
import com.cucumber.verify.ResultVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.ACCOUNT_TO_CHANGE_PASSWORD;
import static com.alpha.common.controller.Urls.SEPARATOR;
import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;

/**
 * Created by jzhou237 on 2017-03-13.
 */
@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class ChangePasswordPage {

    @Autowired
    private UiDriver uiDriver;

    @Value("${label.success}")
    private String success;

    public void navigate(String id) {
        uiDriver.navigateTo(ACCOUNT_TO_CHANGE_PASSWORD + SEPARATOR + id);
    }

    public void inputTwicePassword(String password1, String password2) {
        UiElement element = uiDriver.findElementByName("password");
        element.sendKeys(password1);
        uiDriver.findElementByName("repeatPassword").sendKeys(password2);
        element.submit();
    }

    public void success() {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), success);
    }

    public void fail(String message) {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), message);
    }
}
