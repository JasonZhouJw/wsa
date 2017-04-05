package com.cucumber.driver.elements;

import org.openqa.selenium.WebElement;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class UiLink {

    private final WebElement element;

    public UiLink(WebElement element) {
        this.element = element;
    }

    public void click() {
        element.click();
    }

    public String getText() {
        return element.getText();
    }


}
