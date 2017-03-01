package com.steps.account;

import com.cucumber.driver.UiDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alpha.common.controller.Urls.CREATE_USER;

/**
 * Created by jzhou237 on 2017-03-01.
 */
public class CreateUser {

    @Autowired
    UiDriver uiDriver;

    @Given("^to createUser page$")
    public void to_createUser_page() throws Throwable {
        uiDriver.navigateTo(CREATE_USER);
    }

    @When("^input username \"([^\"]*)\" and input password  \"([^\"]*)\" twice$")
    public void input_username_and_input_password_twice(String arg1, String arg2) throws Throwable {
        // TODO: 2017-03-01  
    }

    @Then("^show message \"([^\"]*)\"$")
    public void show_message(String arg1) throws Throwable {
        // TODO: 2017-03-01  
    }
}
