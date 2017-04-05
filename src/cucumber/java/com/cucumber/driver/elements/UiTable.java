package com.cucumber.driver.elements;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-04-03.
 */
public class UiTable {

    private WebElement table;

    private boolean hasAllData = false;

    private List<UiRow> rowList = new ArrayList<>();

    public UiTable(WebElement element) {
        this.table = element;
    }

    public void getAllData() {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 0; i < rows.size(); i++) {
            rowList.add(new UiRow(rows.get(i)));
        }
        hasAllData = true;
    }

    private UiElement getCellDirect(int row, int column) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        if (CollectionUtils.isNotEmpty(rows)) {
            List<WebElement> columns = rows.get(row).findElements(By.tagName("td"));
            if (CollectionUtils.isNotEmpty(columns)) {
                return new UiElement(columns.get(column));
            }
        }
        return null;
    }

    private UiElement getCellFromAll(int row, int column) {
        return this.rowList.get(row).getColumnList().get(column);
    }

    public UiElement getCell(int row, int column) {
        if (hasAllData) {
            return getCellFromAll(row, column);
        } else {
            return getCellDirect(row, column);
        }
    }

    public String getCellText(int row, int column) {
        UiElement element = this.getCell(row, column);
        return element.getText();
    }
}
