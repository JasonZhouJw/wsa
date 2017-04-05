package com.cucumber.asserts;


import com.cucumber.driver.elements.UiElement;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by jzhou237 on 2017-03-01.
 */
public class WebAssert {

    public static final void ContainText(UiElement uiElement, String text) {
        if (!uiElement.getText().contains(text)) {
            fail();
        }
    }

    public static final void NotContainText(List<UiElement> elementList, String text) {
        elementList.forEach(uiElement -> {
            if (uiElement.getText().equals(text)) {
                fail();
            }
        });
    }
}
