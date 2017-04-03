package com.cucumber.driver;

import com.alpha.common.view.Params;
import com.cucumber.driver.elements.WebTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class SeleniumWebDriver implements UiDriver {

    private static final int DEFAULT_TIME_OUT_IN_SECONDS = 10;
    private final WebDriver webDriver;

    public SeleniumWebDriver() {
        File pathToBinary = new File("C:\\Users\\jzhou237\\AppData\\Local\\Mozilla Firefox\\Firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        webDriver.get(url);
    }

    @Override
    public UiElement findElementByName(String name) {
        return new SeleniumWebElement(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return new SeleniumWebElement(webDriver.findElement(By.tagName(tag)));
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        webDriver.get(url + params.getQuery());
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return new SeleniumSelect(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementById(String id) {
        return new SeleniumWebElement(webDriver.findElement(By.id(id)));
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
    public WebTable findTableByTag(String tag) {
        return new WebTable(webDriver.findElement(By.tagName(tag)));
    }
}
