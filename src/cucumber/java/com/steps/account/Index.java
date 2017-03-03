package com.steps.account;

import com.cucumber.driver.UiDriver;
import com.cucumber.verify.ResultVerify;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alpha.common.controller.Urls.ACCOUNT_INDEX;

/**
 * Created by jzhou237 on 2017-03-03.
 */
public class Index {

    @Autowired
    UiDriver uiDriver;

    @Given("^navigate to index page$")
    public void navigate_to_index_page() throws Throwable {
        uiDriver.navigateTo(ACCOUNT_INDEX);
    }

    @Then("^list the all uses$")
    public void list_the_all_uses() throws Throwable {
        ResultVerify.ContainText(uiDriver.findElementByTag("table"), "admin");
    }
}
