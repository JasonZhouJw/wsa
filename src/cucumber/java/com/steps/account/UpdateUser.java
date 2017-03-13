package com.steps.account;

import com.cucumber.pages.account.UpdatePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-03-13.
 */
public class UpdateUser {

    @Autowired
    private UpdatePage updatePage;

    @Given("^navigate to Update user page with User ID \"([^\"]*)\"$")
    public void navigate_to_Update_user_page_with_User_ID(String arg1) throws Throwable {
        updatePage.navigate(arg1);
    }

    @When("^input password  \"([^\"]*)\" twice$")
    public void input_password_twice(String arg1) throws Throwable {
        updatePage.inputTwicePassword(arg1, arg1);
    }

    @Then("^display \"([^\"]*)\"$")
    public void display(String arg1) throws Throwable {
        updatePage.success(arg1);
    }

}
