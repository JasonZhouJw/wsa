package com.steps.serviceinfo;

import com.alpha.services.entities.ServiceInfo;
import com.cucumber.data.serviceinfo.ServiceInfoForTestRepository;
import com.cucumber.pages.serviceinfo.ServiceInfoCreatePage;
import com.cucumber.pages.serviceinfo.ServiceInfoIndexPage;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jzhou237 on 2017-04-10.
 */
public class Create {

    @Autowired
    private ServiceInfoCreatePage serviceInfoCreatePage;

    @Autowired
    private ServiceInfoIndexPage serviceInfoIndexPage;

    @Autowired
    private ServiceInfoForTestRepository serviceInfoForTestRepository;
    private ServiceInfo serviceInfo;

    @Given("^navigate to create Service Info page$")
    public void navigate_to_create_Service_Info_page() throws Throwable {
        serviceInfoCreatePage.navigate();
    }

    @When("^input wsdl \"([^\"]*)\" and input name \"([^\"]*)\" on CreateServiceInfoPage, submit form$")
    public void input_wsdl_and_input_name_on_CreateServiceInfoPage_submit_form(String arg1, String arg2) throws Throwable {
        this.serviceInfo = serviceInfoCreatePage.inputAndSubmit(arg1, arg2);
    }

    @When("^input wsdl \"([^\"]*)\" and input name \"([^\"]*)\" on CreateServiceInfoPage, click save and assemble$")
    public void input_wsdl_and_input_name_on_CreateServiceInfoPage_click_save_and_assemble(String arg1, String arg2) throws Throwable {
        this.serviceInfo = serviceInfoCreatePage.inputAndAssemble(arg1, arg2);
    }

    @Then("^display \"([^\"]*)\" on CreateServiceInfoPage$")
    public void display_on_CreateServiceInfoPage(String arg1) throws Throwable {
        serviceInfoCreatePage.displayMessage(arg1);
    }

    @Then("^click return button on create Service Info page$")
    public void click_return_button_on_create_Service_Info_page() throws Throwable {
        serviceInfoCreatePage.clickReturn();
    }

    @Then("^display wsdl \"([^\"]*)\" in column \"([^\"]*)\" row \"([^\"]*)\" on Service Info index page$")
    public void display_wsdl_in_column_row_on_Service_Info_index_page(String arg1, String arg2, String arg3) throws Throwable {
        serviceInfoIndexPage.display(arg1, Integer.valueOf(arg2), Integer.valueOf(arg3));
    }

    @After("@ServiceInfoCreate")
    @Transactional
    public void afterAll() {
        if (serviceInfo != null) {
            this.serviceInfoForTestRepository.deleteByWsdl(serviceInfo.getWsdl());
        }
    }
}
