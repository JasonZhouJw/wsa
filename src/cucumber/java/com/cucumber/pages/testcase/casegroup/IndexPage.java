package com.cucumber.pages.testcase.casegroup;

import com.alpha.testcase.entities.CaseGroup;
import com.cucumber.asserts.WebAssert;
import com.cucumber.driver.elements.UiTable;
import com.cucumber.pages.AbstractPage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_INDEX;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Component("caseGroupIndexPage")
@Scope("cucumber-glue")
public class IndexPage extends AbstractPage {

    public void navigate() {
        uiDriver.navigateTo(CASE_GROUP_INDEX);
    }

    public void showData(Integer row, Integer column, CaseGroup savedCaseGroup) {
        UiTable table = uiDriver.findTableByTag("tbody");
        WebAssert.ContainText(table.getCell(row, column), savedCaseGroup.getName());
    }
}
