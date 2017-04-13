package com.cucumber.pages.serviceinfo;

import com.alpha.services.entities.ServiceInfo;
import com.cucumber.driver.UiDriver;
import com.cucumber.pages.AbstractPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.SERVICE_INFO_TO_CREATE;

/**
 * Created by jzhou237 on 2017-04-10.
 */
@Component
@Scope("cucumber-glue")
public class ServiceInfoCreatePage extends AbstractPage {

    @Autowired
    private UiDriver uiDriver;

    public void navigate() {
        uiDriver.navigateTo(SERVICE_INFO_TO_CREATE);
    }

    public ServiceInfo inputAndSubmit(String wsdl, String name) {
        uiDriver.findElementByName("name").sendKeys(name);
        uiDriver.findElementByName("wsdl").sendKeys(wsdl);
        uiDriver.findElementById("create").submit();
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setWsdl(wsdl);
        return serviceInfo;
    }

    public void clickReturn() {
        uiDriver.findLinkById("return").click();
    }

    public ServiceInfo inputAndAssemble(String wsdl, String name) {
        uiDriver.findElementByName("name").sendKeys(name);
        uiDriver.findElementByName("wsdl").sendKeys(wsdl);
        uiDriver.findElementById("createAndAssemble").submit();
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setWsdl(wsdl);
        return serviceInfo;
    }
}
