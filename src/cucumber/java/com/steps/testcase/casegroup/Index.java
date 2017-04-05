package com.steps.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.data.CaseGroupForTestRepository;
import com.cucumber.pages.testcase.casegroup.IndexPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

/**
 * Created by jzhou237 on 2017-03-30.
 */
public class Index {

    @Autowired
    @Qualifier("caseGroupIndexPage")
    private IndexPage indexPage;

    @Autowired
    private CaseGroupForTestRepository repository;

    private CaseGroup savedCaseGroup;

    @Before("@CaseGroupIndex")
    public void setup() {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName(UUID.randomUUID().toString());
        savedCaseGroup = this.repository.save(caseGroup);
    }

    @Given("^navigate to Case Group Index page$")
    public void navigate_to_Case_Group_Index_page() throws Throwable {
        indexPage.navigate();
    }

    @Then("^display no data message in Case Group Index Page$")
    public void display_no_data_message_in_case_group_index_page() throws Throwable {
        indexPage.displayNoData();
    }

    @Then("^should display test case group in row \"([^\"]*)\" and column \"([^\"]*)\"$")
    public void should_display_test_case_group_in_row_and_column(String arg1, String arg2) throws Throwable {
        indexPage.showData(Integer.valueOf(arg1), Integer.valueOf(arg2), savedCaseGroup);
    }

    @Then("^back to case group Index page$")
    public void back_to_case_group_Index_page() throws Throwable {
        indexPage.previous("Test Case");
    }

    @After("@CaseGroupIndex")
    public void afterAll() {
        this.repository.delete(savedCaseGroup);
    }
}
