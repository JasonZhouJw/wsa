package com.steps.account;

import com.cucumber.driver.UiDriver;
import com.cucumber.pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alpha.common.controller.Urls.LOGIN;
import static com.cucumber.asserts.WebAssert.ContainText;

/**
 * Created by jzhou237 on 2017-03-01.
 */
public class Login {

    @Autowired
    UiDriver uiDriver;

    @Autowired
    private LoginPage loginPage;

    @Given("^to login page$")
    public void to_login_page() throws Throwable {
        uiDriver.navigateTo(LOGIN);
    }

    @When("^input username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void input_username_and_password(String arg1, String arg2) throws Throwable {
        loginPage.login(arg1, arg2);
    }

    @Then("^login page, show message \"([^\"]*)\"$")
    public void login_page_show_message(String arg1) throws Throwable {
        ContainText(uiDriver.findElementByTag("body"), arg1);
    }
}
