package com.steps.account;

import com.cucumber.driver.UiDriver;
import com.cucumber.pages.CreateAccountPage;
import com.cucumber.verify.ResultVerify;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alpha.common.controller.Urls.TO_CREATE_ACCOUNT;

/**
 * Created by jzhou237 on 2017-03-01.
 */
public class CreateUser {

    @Autowired
    UiDriver uiDriver;

    @Autowired
    CreateAccountPage createAccountPage;

    @Given("^to createUser page$")
    public void to_createUser_page() throws Throwable {
        uiDriver.navigateTo(TO_CREATE_ACCOUNT);
    }

    @When("^input username \"([^\"]*)\" and input password  \"([^\"]*)\" twice$")
    public void input_username_and_input_password_twice(String arg1, String arg2) throws Throwable {
        createAccountPage.createNewAccount(arg1, arg2);
    }

    @Then("^show message \"([^\"]*)\"$")
    public void show_message(String arg1) throws Throwable {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), "Success");
    }
}
