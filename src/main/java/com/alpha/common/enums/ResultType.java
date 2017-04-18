package com.alpha.common.enums;

import com.alpha.common.model.Option;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-09-21.
 */
@Getter
public enum ResultType {

    SUCCESS("SUCCESS", "label.success"),
    FAIL("FAIL", "label.fail"),
    ERROR("DANGER", "label.danger");

    private String type;

    private String label;

    ResultType(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public static List<Option> options() {
        List<Option> optionList = new ArrayList<>();
        for (ResultType element : ResultType.values()) {
            optionList.add(new Option(element.getLabel(), element.getType()));
        }
        return optionList;
    }

    @Override
    public String toString() {
        return type;
    }

}
