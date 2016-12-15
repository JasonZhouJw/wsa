package com.steps;

import com.driver.UiDriver;
import com.driver.UiDriverWithHostName;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by jzhou237 on 2016-12-13.
 */
public class Home {

    private UiDriver uiDriver = new UiDriverWithHostName();

    @When("^show home page$")
    public void show_home_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        uiDriver.navigateTo("/home");
    }

    @Then("^display home page, show some message$")
    public void display_home_page_show_some_message() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }

}
