package com.steps.testcase;

import com.cucumber.pages.testcase.IndexPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by jzhou237 on 2017-03-20.
 */
public class Index {

    @Autowired
    @Qualifier("testCaseIndexPage")
    public IndexPage indexPage;

    @Given("^navigate to TestCase Index page$")
    public void navigate_to_TestCase_Index_page() throws Throwable {
        indexPage.navigate();
    }

    @Then("^Index page should display \"([^\"]*)\"$")
    public void index_page_should_display(String arg1) throws Throwable {
        indexPage.noData(arg1);
    }

    @When("^input name \"([^\"]*)\" and select \"([^\"]*)\"$")
    public void input_name_and_select(String arg1, String arg2) throws Throwable {
        indexPage.searchByNameAndActive(arg1, arg2);
    }
}
