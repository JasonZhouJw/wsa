package com.steps;

import com.annotation.UserLogin;
import com.driver.AbstractSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by jzhou237 on 2016-12-13.
 */

public class Home extends AbstractSteps {

    @UserLogin
    @When("^show home page$")
    public void show_home_page() throws Throwable {
        uiDriver.navigateTo("/");
    }

    @Then("^display home page, show some message$")
    public void display_home_page_show_some_message() throws Throwable {
//        throw new Exception("exception");
    }

}
