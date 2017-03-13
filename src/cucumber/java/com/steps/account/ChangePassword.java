package com.steps.account;

import com.cucumber.pages.account.ChangePasswordPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-03-13.
 */
public class ChangePassword {

    @Autowired
    private ChangePasswordPage changePasswordPage;

    @Given("^navigate to ChangePassword page with User ID \"([^\"]*)\"$")
    public void navigate_to_Update_user_page_with_User_ID(String arg1) throws Throwable {
        changePasswordPage.navigate(arg1);
    }

    @When("^input password  \"([^\"]*)\" twice$")
    public void input_password_twice(String arg1) throws Throwable {
        changePasswordPage.inputTwicePassword(arg1, arg1);
    }

    @Then("^display \"([^\"]*)\"$")
    public void display() throws Throwable {
        changePasswordPage.success();
    }

    @When("^input password \"([^\"]*)\" and input repeatPassword \"([^\"]*)\"$")
    public void input_password_and_input_repeatPassword(String arg1, String arg2) throws Throwable {
        changePasswordPage.inputTwicePassword(arg1, arg2);
    }

    @Then("^success$")
    public void success() throws Throwable {
        changePasswordPage.success();
    }

    @Then("^update fail, display error message \"([^\"]*)\"$")
    public void update_fail_display_error_message(String arg1) throws Throwable {
        changePasswordPage.fail(arg1);
    }

}
