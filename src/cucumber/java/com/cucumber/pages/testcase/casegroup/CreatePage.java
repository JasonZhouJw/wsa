package com.cucumber.pages.testcase.casegroup;

import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.UiElement;
import com.cucumber.pages.AbstractPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_TO_CREATE;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@Component("caseGroupCreatePage")
@Scope("cucumber-glue")
public class CreatePage extends AbstractPage {

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


}
