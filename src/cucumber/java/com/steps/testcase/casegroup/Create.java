package com.steps.testcase.casegroup;

import com.cucumber.data.CaseGroupForTextRepository;
import com.cucumber.pages.testcase.casegroup.CreateCaseGroupPage;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.fail;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@CucumberOptions
public class Create {

    @Autowired
    private CreateCaseGroupPage createCaseGroupPage;

    @Autowired
    private CaseGroupForTextRepository repository;

    private String savedName;

    @Given("^navigate to createCaseGroup page$")
    public void navigate_to_createCaseGroup_page() throws Throwable {
        createCaseGroupPage.navigate();
    }

    @When("^input name \"([^\"]*)\"$")
    public void input_name(String arg1) throws Throwable {
        this.savedName = arg1;
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

    @Then("^createCasePage display \"([^\"]*)\"$")
    public void createcasepage_display(String arg1) throws Throwable {
        createCaseGroupPage.displayMessage(arg1);
    }

    @After
    @Transactional
    public void afterAll() {
        if (StringUtils.isNoneBlank(this.savedName)) {
            this.repository.deleteByName(this.savedName);
        } else {
            fail();
        }
    }
}
