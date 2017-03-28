package com.alpha.common.enums;

import com.alpha.common.model.Option;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-03-17.
 */
@Getter
public enum Active {

    TRUE(true, "label.active.true", 1),
    FALSE(false, "label.active.false", 0);

    private boolean active;

    private String label;

    private int realValue;

    Active(boolean active, String label, int realValue) {
        this.active = active;
        this.label = label;
        this.realValue = realValue;
    }

    public static List<Option> options() {
        List<Option> optionList = new ArrayList<>();
        for (Active element : Active.values()) {
            optionList.add(new Option(element.getLabel(), String.valueOf(element.isActive())));
        }
        return optionList;
    }

    public static Active getStatus(boolean active) {
        return active ? TRUE : FALSE;
    }

}
