package com.cucumber.driver.elements;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class UiElement {

    private final org.openqa.selenium.WebElement element;

    public UiElement(org.openqa.selenium.WebElement element) {
        this.element = element;
    }

    public void sendKeys(String keys) {
        element.sendKeys(keys);
    }

    public void submit() {
        element.submit();
    }

    public String getText() {
        return element.getText();
    }
}
