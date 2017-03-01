package com.cucumber.verify;

import com.cucumber.driver.UiElement;

import static org.junit.Assert.fail;

/**
 * Created by jzhou237 on 2017-03-01.
 */
public class ResultVerify {

    public static final void ContainText(UiElement uiElement, String text) {
        if (!uiElement.getText().contains(text)) {
            fail();
        }
    }
}
