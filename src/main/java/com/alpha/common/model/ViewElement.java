package com.alpha.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@Setter
@Getter
public class ViewElement {


    protected String label;

    protected String value;

    public ViewElement() {

    }

    public ViewElement(String label) {
        this(label, null);
    }

    public ViewElement(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
