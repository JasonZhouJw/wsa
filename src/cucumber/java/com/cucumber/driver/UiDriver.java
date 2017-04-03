package com.cucumber.driver;


import com.alpha.common.view.Params;
import com.cucumber.driver.elements.WebTable;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    UiElement findElementByName(String name);

    UiElement findElementByTag(String tag);

    void navigateToWithParams(String url, Params params);

    UiSelect findSelectByName(String name);

    UiElement findElementById(String id);

    void waitForTextPresent(String text);

    WebTable findTableByTag(String tag);
}
