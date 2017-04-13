package com.steps.serviceinfo;

import com.cucumber.pages.serviceinfo.ServiceInfoIndexPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-04-10.
 */
public class Index {

    @Autowired
    private ServiceInfoIndexPage serviceInfoIndexPage;

    @Given("^navigate to Service Info index page$")
    public void navigate_to_Service_Info_index_page() throws Throwable {
        serviceInfoIndexPage.navigate();
    }

    @Then("^display \"([^\"]*)\" on service info index page$")
    public void display_on_service_info_index_page(String arg1) throws Throwable {
        serviceInfoIndexPage.display(arg1);
    }
}
