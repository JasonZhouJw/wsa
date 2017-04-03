package com.cucumber.pages;


import com.cucumber.driver.UiDriver;
import com.cucumber.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.LOGIN;
import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;


@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class LoginPage {

    @Autowired
    UiDriver driver;

    @Value("${label.login.head}")
    String headMessage;

    public void login(String userName, String password) {
        driver.navigateTo(LOGIN);
        driver.waitForTextPresent(headMessage);
        setPassword(password);
        setUserNameAndSubmit(userName);
    }

    private void setUserNameAndSubmit(String userName) {
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
        userNameTextBox.submit();
    }

    private void setPassword(String password) {
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
    }

}
