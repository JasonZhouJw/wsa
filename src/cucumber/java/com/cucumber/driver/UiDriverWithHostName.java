package com.cucumber.driver;

import com.alpha.common.view.Params;
import com.cucumber.driver.elements.UiElement;
import com.cucumber.driver.elements.UiLink;
import com.cucumber.driver.elements.UiSelect;
import com.cucumber.driver.elements.UiTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class UiDriverWithHostName implements UiDriver {

    public static final String DELIMITER = ":";
    private static final int DEFAULT_TIME_OUT_IN_SECONDS = 10;
    private final String hostName = "http://localhost";
    private final WebDriver webDriver = DriverFactory.getDriver(DriverFactory.Browser.FIRE_FOX);
    private String port = "8080";

    public UiDriverWithHostName() {
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        webDriver.get(urlWithHostAndPort(url));
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        webDriver.get(urlWithHostAndPort(url) + params.getQuery());
    }

    @Override
    public void waitForTextPresent(String text) {
        new WebDriverWait(webDriver, DEFAULT_TIME_OUT_IN_SECONDS).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return findElementByTag("body").getText().contains(text);
            }
        });
    }

    @Override
    public UiElement findElementByName(String name) {
        return new UiElement(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return new UiElement(webDriver.findElement(By.tagName(tag)));
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return new UiSelect(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementById(String id) {
        return new UiElement(webDriver.findElement(By.id(id)));
    }

    @Override
    public UiTable findTableByTag(String tag) {
        return new UiTable(webDriver.findElement(By.tagName(tag)));
    }

    @Override
    public UiLink findLinkById(String id) {
        return new UiLink(webDriver.findElement(By.id(id)));
    }

    protected String urlWithHostAndPort(String url) {
        return hostName + DELIMITER + port + url;
    }

}
