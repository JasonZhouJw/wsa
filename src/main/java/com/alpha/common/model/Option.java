package com.alpha.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@ToString
@Getter
@Setter
public class Option extends ViewElement {

    private String selected;

    public Option(String label, String value, Function<String, Boolean> function) {
        super(label, value);
        if (function != null && function.apply(value)) {
            selected = "selected";
        }
    }

    public Option(String label, String value) {
        this(label, value, null);
    }

}
