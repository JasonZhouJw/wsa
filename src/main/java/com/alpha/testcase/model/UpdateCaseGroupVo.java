package com.alpha.testcase.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-04-03.
 */
public class UpdateCaseGroupVo extends CaseGroupVo {

    @NotNull
    @Min(value = 1)
    @Override
    public Long getId() {
        return super.getId();
    }
}
