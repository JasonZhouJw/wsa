package com.steps.testcase.casegroup;

import com.cucumber.pages.testcase.casegroup.CreateCaseGroupPage;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@CucumberOptions
public class Create {

    @Autowired
    private CreateCaseGroupPage createCaseGroupPage;

    @Given("^navigate to createCaseGroup page$")
    public void navigate_to_createCaseGroup_page() throws Throwable {
        createCaseGroupPage.navigate();
    }

    @When("^input name \"([^\"]*)\"$")
    public void input_name(String arg1) throws Throwable {
        createCaseGroupPage.inputNameAndSubmit(arg1);
    }

    @When("^click return button$")
    public void click_return_button() throws Throwable {
        createCaseGroupPage.back();
    }

    @Then("^back to Index page$")
    public void back_to_Index_page() throws Throwable {
        createCaseGroupPage.previous("Test Case");
    }
}
