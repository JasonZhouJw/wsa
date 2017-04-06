package com.alpha.testcase.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2017-04-06.
 */
public class UpdateTestCaseVo extends TestCaseVo {

    @NotNull
    @Override
    public Long getId() {
        return super.getId();
    }

    @Length(max = 4000)
    @Override
    public String getRequestValue() {
        return super.getRequestValue();
    }

    @Length(max = 4000)
    @Override
    public String getVerification() {
        return super.getVerification();
    }

    @Length(max = 200)
    @Override
    public String getName() {
        return super.getName();
    }
}
