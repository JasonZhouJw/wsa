package com.alpha.testcase.model;

import com.alpha.testcase.entities.TestCase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Getter
@Setter
@ToString
public class TestCaseVo {

    private Long id;

    private String name;

    private Long interfaceInfoId;

    private String requestValue;

    private String verification;

    public static TestCaseVo toVo(TestCase testCase) {
        TestCaseVo vo = new TestCaseVo();
        if (testCase != null) {
            vo.setName(testCase.getName());
            vo.setRequestValue(testCase.getRequestValue());
            vo.setVerification(testCase.getVerification());
        }
        return vo;
    }

    public static List<TestCaseVo> toVo(List<TestCase> testCaseList) {
        List<TestCaseVo> voList = new ArrayList<TestCaseVo>();
        testCaseList.forEach(testCase -> {
            voList.add(toVo(testCase));
        });
        return voList;
    }

    public TestCase toDo() {
        TestCase testCase = new TestCase();
        testCase.setId(this.id);
        testCase.setRequestValue(this.requestValue);
        testCase.setVerification(this.verification);
        testCase.setName(this.name);
        return testCase;
    }

}
