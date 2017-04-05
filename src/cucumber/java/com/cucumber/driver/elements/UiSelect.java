package com.cucumber.driver.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class UiSelect {

    private final WebElement element;

    public UiSelect(WebElement element) {
        this.element = element;
    }

    public void selectByVisibleText(String text) {
        new Select(element).selectByVisibleText(text);
    }
}
