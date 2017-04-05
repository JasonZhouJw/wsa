package com.cucumber.pages.testcase;

import com.cucumber.asserts.WebAssert;
import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.TEST_CASE_INDEX;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@Component("testCaseIndexPage")
@Scope("cucumber-glue")
public class IndexPage {

    @Autowired
    private UiDriver uiDriver;

    public void navigate() {
        uiDriver.navigateTo(TEST_CASE_INDEX);
    }


    public void noData(String message) {
        WebAssert.ContainText(uiDriver.findElementByTag("body"), message);
    }

    public void searchByNameAndActive(String name, String active) {
        UiElement uiElement = uiDriver.findElementById("name");
        uiElement.sendKeys(name);
        uiDriver.findSelectByName("active").selectByVisibleText(active);
        uiElement.submit();
    }
}
