package com.cucumber.driver.elements;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-04-03.
 */
@Getter
@Setter
public class UiRow {
    private final WebElement element;
    private List<UiElement> columnList = new ArrayList<>();

    public UiRow(WebElement element) {
        this.element = element;
        this.getAllColumn();
    }

    private void getAllColumn() {
        List<WebElement> columns = element.findElements(By.tagName("td"));
        for (WebElement column : columns) {
            columnList.add(new UiElement(column));
        }
    }
}
