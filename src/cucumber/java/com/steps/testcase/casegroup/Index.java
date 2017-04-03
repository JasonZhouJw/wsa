package com.steps.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.data.CaseGroupForTextRepository;
import com.cucumber.pages.testcase.casegroup.IndexPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by jzhou237 on 2017-03-30.
 */
public class Index {

    @Autowired
    private IndexPage indexPage;

    @Autowired
    private CaseGroupForTextRepository repository;

    private CaseGroup savedCaseGroup;

    @Before
    public void setup() {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName(UUID.randomUUID().toString());
        savedCaseGroup = this.repository.save(caseGroup);
    }

    @Given("^navigate to Case Group Index page$")
    public void navigate_to_Case_Group_Index_page() throws Throwable {
        indexPage.navigate();
    }

    @Then("^display no data message$")
    public void display_no_data_message() throws Throwable {
        indexPage.displayNoData();
    }

    @Then("^should display test case group in row \"([^\"]*)\" and column \"([^\"]*)\"$")
    public void should_display_test_case_group_in_row_and_column(String arg1, String arg2) throws Throwable {
        indexPage.showData(Integer.valueOf(arg1), Integer.valueOf(arg2), savedCaseGroup);
    }

    @After
    public void afterAll() {
        this.repository.delete(savedCaseGroup);
    }
}
