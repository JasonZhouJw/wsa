package com.cucumber.driver.elements;

import com.cucumber.driver.SeleniumWebElement;
import com.cucumber.driver.UiElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-04-03.
 */
@Getter
@Setter
public class WebRow {
    List<UiElement> columnList = new ArrayList<>();
    private int index;

    public WebRow(int index) {
        this.index = index;
    }

    public void addColumn(WebElement element) {
        this.columnList.add(new SeleniumWebElement(element));
    }
}
