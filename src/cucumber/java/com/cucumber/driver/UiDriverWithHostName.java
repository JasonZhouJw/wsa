package com.cucumber.driver;

import com.alpha.common.view.Params;
import com.cucumber.driver.elements.WebTable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class UiDriverWithHostName implements UiDriver {

    public static final String DELIMITER = ":";
    private final String hostName = "http://localhost";
    private final UiDriver originalDriver = new SeleniumWebDriver();
    private String port = "8080";

    public UiDriverWithHostName() {
        System.out.println("initial");
    }

    @Override
    public void close() {
        originalDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        originalDriver.navigateTo(urlWithHostAndPort(url));
    }

    public String urlWithHostAndPort(String url) {
        return hostName + DELIMITER + port + url;
    }

    @Override
    public UiElement findElementByName(String name) {
        return originalDriver.findElementByName(name);
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return originalDriver.findElementByTag(tag);
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        originalDriver.navigateToWithParams(urlWithHostAndPort(url), params);
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return originalDriver.findSelectByName(name);
    }

    @Override
    public UiElement findElementById(String id) {
        return originalDriver.findElementById(id);
    }

    @Override
    public void waitForTextPresent(String text) {
        originalDriver.waitForTextPresent(text);
    }

    @Override
    public WebTable findTableByTag(String tag) {
        return originalDriver.findTableByTag(tag);
    }
}
