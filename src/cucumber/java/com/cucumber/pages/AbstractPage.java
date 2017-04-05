package com.cucumber.pages;

import com.cucumber.asserts.WebAssert;
import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.UiLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jzhou237 on 2017-04-05.
 */
@Slf4j
public class AbstractPage {

    @Autowired
    protected UiDriver uiDriver;

    public void displayNoData() {
        WebAssert.ContainText(uiDriver.findElementByTag("body"), "There is no data yet");
    }

    public void back() {
        UiLink link = uiDriver.findLinkById("return");
        link.click();
    }

    public void previous(String message) {
        WebAssert.ContainText(uiDriver.findElementByTag("body"), message);
    }

    public void displayMessage(String message) {
        WebAssert.ContainText(uiDriver.findElementByTag("body"), message);
    }
}
