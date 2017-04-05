package com.steps.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.data.CaseGroupForTestRepository;
import com.cucumber.pages.testcase.casegroup.CreatePage;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@CucumberOptions
public class Create {

    @Autowired
    @Qualifier("caseGroupCreatePage")
    private CreatePage createPage;

    @Autowired
    private CaseGroupForTestRepository repository;

    private String savedName;

    private CaseGroup existCaseGroup;

    @Before("@CreateCaseGroup")
    public void setup() {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName("existing");
        this.existCaseGroup = this.repository.save(caseGroup);
    }

    @Given("^navigate to createCaseGroup page$")
    public void navigate_to_createCaseGroup_page() throws Throwable {
        createPage.navigate();
    }

    @When("^input case group name \"([^\"]*)\"$")
    public void input_case_group_name(String arg1) throws Throwable {
        this.savedName = arg1;
        createPage.inputNameAndSubmit(arg1);
    }

    @When("^click return button on create case group page$")
    public void click_return_button_on_create_case_group_page() throws Throwable {
        createPage.back();
    }


    @Then("^create case page display \"([^\"]*)\"$")
    public void create_case_page_display(String arg1) throws Throwable {
        createPage.displayMessage(arg1);
    }

    @When("^input existing case group name$")
    public void input_existing_case_group_name() throws Throwable {
        createPage.inputNameAndSubmit(this.existCaseGroup.getName());
    }

    @After("@CreateCaseGroup")
    @Transactional
    public void afterAll() {
        this.repository.delete(this.existCaseGroup);
        if (StringUtils.isNotEmpty(this.savedName)) {
            this.repository.deleteByName(this.savedName);
        }
    }
}
