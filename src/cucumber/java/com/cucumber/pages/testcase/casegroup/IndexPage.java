package com.cucumber.pages.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.driver.UiDriver;
import com.cucumber.driver.elements.WebTable;
import com.cucumber.verify.ResultVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_INDEX;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Component
@Scope("cucumber-glue")
public class IndexPage {

    @Autowired
    private UiDriver uiDriver;


    public void navigate() {
        uiDriver.navigateTo(CASE_GROUP_INDEX);
    }

    public void displayNoData() {
        ResultVerify.ContainText(uiDriver.findElementByTag("body"), "There is no data yet");
    }

    public void showData(Integer row, Integer column, CaseGroup savedCaseGroup) {
        WebTable table = uiDriver.findTableByTag("tbody");
        ResultVerify.ContainText(table.getCell(row, column), savedCaseGroup.getName());
    }
}
