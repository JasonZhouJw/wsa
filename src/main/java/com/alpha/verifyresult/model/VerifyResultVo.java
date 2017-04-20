package com.alpha.verifyresult.model;

import com.alpha.common.enums.ResultType;
import com.alpha.testcase.model.TestCaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@Getter
@Setter
@ToString(exclude = {"testCase"})
public class VerifyResultVo {

    private Long id;

    private Date executedTime = new Date();

    private Date updatedTime = new Date();

    private TestCaseVo testCase;

    private ResultType result = ResultType.SUCCESS;

    private String message;

}
