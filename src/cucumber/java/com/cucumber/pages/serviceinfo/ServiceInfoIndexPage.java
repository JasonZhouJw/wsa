package com.cucumber.pages.serviceinfo;

import com.cucumber.asserts.WebAssert;
import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.UiTable;
import com.cucumber.pages.AbstractPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.SERVICE_INFO_INDEX;

/**
 * Created by jzhou237 on 2017-04-10.
 */
@Component
@Scope("cucumber-glue")
public class ServiceInfoIndexPage extends AbstractPage {

    @Autowired
    private UiDriver uiDriver;


    public void navigate() {
        uiDriver.navigateTo(SERVICE_INFO_INDEX);
    }

    public void display(String arg1) {
        WebAssert.ContainText(uiDriver.findElementByTag("body"), arg1);
    }

    public void display(String value, Integer column, Integer row) {
        UiTable uiTable = uiDriver.findTableByTag("tbody");
        WebAssert.ContainText(uiTable.getCell(row, column), value);
    }
}
