package com.alpha.common.enums;

import com.alpha.common.view.ILabel;
import lombok.Getter;

/**
 * Created by jzhou237 on 2017-03-17.
 */
@Getter
public enum Active implements ILabel {

    TRUE(true, "label.active.true", 1),
    FALSE(false, "label.active.false", 0);

    private boolean active;

    private String labelKey;

    private String label;

    private int realValue;

    Active(boolean active, String labelKey, int realValue) {
        this.active = active;
        this.labelKey = labelKey;
        this.realValue = realValue;
    }

    public Active getStatus(boolean active) {
        return active ? TRUE : FALSE;
    }

    @Override
    public String getLabelKey() {
        return this.labelKey;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
