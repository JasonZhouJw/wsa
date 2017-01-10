package com.pages;


import com.driver.UiDriver;
import com.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;


@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class SignInPage {

    @Autowired
    UiDriver driver;

    @Value("${signin.label.head}")
    String headMessage;

    public void signIn(String userName, String password) {
        System.out.println(userName + ":" + password);
//        driver.navigateTo(SIGN_IN);
//        driver.waitForTextPresent(headMessage);
//        setPassword(password);
//        setUserNameAndSubmit(userName);
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
