package com.cucumber.pages.testcase.casegroup;

import com.cucumber.driver.elements.UiElement;
import com.cucumber.pages.AbstractPage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_TO_UPDATE;
import static com.alpha.common.controller.Urls.SEPARATOR;

/**
 * Created by jzhou237 on 2017-04-05.
 */
@Component("caseGroupUpdatePage")
@Scope("cucumber-glue")
public class UpdatePage extends AbstractPage {

    public void inputNameAndSubmit(String arg1) {
        UiElement element = uiDriver.findElementByName("name");
        element.sendKeys(arg1);
        element.submit();
    }

    public void navigate(Long id) {
        uiDriver.navigateTo(CASE_GROUP_TO_UPDATE + SEPARATOR + id);
    }
}
