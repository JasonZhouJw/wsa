package com.alpha.testcase.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Null;

/**
 * Created by jzhou237 on 2017-03-31.
 */

public class CreateCaseGroupVo extends CaseGroupVo {

    @Override
    @NotEmpty
    @Length(min = 1, max = 100)
    public String getName() {
        return super.getName();
    }

    @Override
    @Null
    public Long getId() {
        return super.getId();
    }
}
