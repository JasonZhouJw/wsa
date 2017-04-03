package com.cucumber.pages.testcase.casegroup;

import com.cucumber.driver.UiDriver;
import com.cucumber.driver.UiElement;
import com.cucumber.verify.ResultVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_TO_CREATE;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@Component
@Scope("cucumber-glue")
public class CreateCaseGroupPage {

    @Autowired
    private UiDriver uiDriver;


    public void navigate() {
        uiDriver.navigateTo(CASE_GROUP_TO_CREATE);
    }

    public void inputNameAndSubmit(String name) {
        UiElement element = uiDriver.findElementByName("name");
        element.sendKeys(name);
        element.submit();
    }

    public void displayMessage(String message) {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), message);
    }

    public void back() {
        UiElement element = uiDriver.findElementById("return");
        element.submit();
    }

    public void previous(String message) {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), message);
    }
}
