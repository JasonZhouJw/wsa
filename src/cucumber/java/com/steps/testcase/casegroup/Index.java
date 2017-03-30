package com.steps.testcase.casegroup;

import com.cucumber.pages.testcase.casegroup.IndexPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-03-30.
 */
public class Index {

    @Autowired
    private IndexPage indexPage;

    @Given("^navigate to Case Group Index page$")
    public void navigate_to_Case_Group_Index_page() throws Throwable {
        indexPage.navigate();
    }

    @Then("^display no data message$")
    public void display_no_data_message() throws Throwable {
        indexPage.displayNoData();
    }
}
