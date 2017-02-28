package com.steps;

import com.cucumber.driver.UiDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2016-12-13.
 */
public class Home {

    @Autowired
    private UiDriver uiDriver;

    @When("^show home page$")
    public void show_home_page() throws Throwable {
        uiDriver.navigateTo("/");
    }

    @Then("^display home page, show some message$")
    public void display_home_page_show_some_message() throws Throwable {

    }

}
