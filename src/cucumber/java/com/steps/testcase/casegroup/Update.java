package com.steps.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.data.CaseGroupForTestRepository;
import com.cucumber.pages.testcase.casegroup.UpdatePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class Update {

    @Autowired
    @Qualifier("caseGroupUpdatePage")
    private UpdatePage updatePage;

    @Autowired
    private CaseGroupForTestRepository repository;

    private CaseGroup updatedCaseGroup;

    private CaseGroup existCaseGroup;

    @Before("@CaseGroupUpdate")
    public void setup() {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setName("cucumber");
        updatedCaseGroup = this.repository.save(caseGroup);

        caseGroup = new CaseGroup();
        caseGroup.setName("existing");
        existCaseGroup = this.repository.save(caseGroup);
    }

    @Then("^update Case group Page should display \"([^\"]*)\"$")
    public void update_Case_group_Page_should_display(String arg1) throws Throwable {
        updatePage.displayMessage(arg1);
    }


    @Given("^navigate to update Case Group page$")
    public void navigate_to_update_Case_Group_page() throws Throwable {
        updatePage.navigate(updatedCaseGroup.getId());
    }

    @When("^modify the case group name \"([^\"]*)\"$")
    public void modify_the_case_group_name(String arg1) throws Throwable {
        updatePage.inputNameAndSubmit(arg1);
    }

    @When("^click return button on update Case Group page$")
    public void click_return_button_on_update_Case_Group_page() throws Throwable {
        updatePage.back();
    }

    @After("@CaseGroupUpdate")
    public void afterAll() {
        this.repository.delete(updatedCaseGroup);
        this.repository.delete(existCaseGroup);
    }
}
