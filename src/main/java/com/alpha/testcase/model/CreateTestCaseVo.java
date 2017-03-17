package com.alpha.testcase.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Created by jzhou237 on 2017-03-16.
 */
public class CreateTestCaseVo extends TestCaseVo {

    @NotEmpty
    @Length(min = 1, max = 200)
    @Override
    public String getName() {
        return super.getName();
    }

    @NotEmpty
    @Range(min = 1L)
    @Override
    public Long getMethodId() {
        return super.getMethodId();
    }

    @NotEmpty
    @Range(min = 1L)
    @Override
    public Long getGroupId() {
        return super.getGroupId();
    }

    @Length(max = 4000)
    @Override
    public String getRequestValue() {
        return super.getRequestValue();
    }

    @NotEmpty
    @Length(min = 1, max = 4000)
    @Override
    public String getVerification() {
        return super.getVerification();
    }
}
