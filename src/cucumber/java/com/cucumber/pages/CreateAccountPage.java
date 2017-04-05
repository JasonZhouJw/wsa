package com.cucumber.pages;

import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;

/**
 * Created by jzhou237 on 2017-03-02.
 */
@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class CreateAccountPage {

    @Autowired
    UiDriver uiDriver;

    public void createNewAccount(String userName, String password) {
        UiElement uiElement = uiDriver.findElementByName("name");
        uiElement.sendKeys(userName);
        uiDriver.findElementByName("password").sendKeys(password);
        uiDriver.findElementByName("repeatPassword").sendKeys(password);
        uiDriver.findElementByName("isAdmin").sendKeys(Boolean.TRUE.toString());
        uiElement.submit();
    }
}
