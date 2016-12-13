package com.driver;

public interface UiElement {
    void sendKeys(String keys);

    void submit();

    String getText();
}
