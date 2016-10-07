package com.alpha.web.model;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
public class TestCaseVo {

    private Long id;

    private String name;

    private Long interfaceInfoId;

    private String requestValue;

    private String verification;

    public static TestCaseVo toVo(TestCase testCase) {
        TestCaseVo vo = new TestCaseVo();
        vo.setInterfaceInfoId(testCase.getInterfaceInfo().getId());
        vo.setName(testCase.getName());
        vo.setRequestValue(testCase.getRequestValue());
        vo.setVerification(testCase.getVerification());
        return vo;
    }

    public static List<TestCaseVo> toVo(List<TestCase> testCaseList) {
        List<TestCaseVo> voList = new ArrayList<TestCaseVo>();
        testCaseList.forEach(testCase -> {
            voList.add(toVo(testCase));
        });
        return voList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInterfaceInfoId() {
        return interfaceInfoId;
    }

    public void setInterfaceInfoId(Long interfaceInfoId) {
        this.interfaceInfoId = interfaceInfoId;
    }

    public String getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(String requestValue) {
        this.requestValue = requestValue;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public TestCase toDo() {
        TestCase testCase = new TestCase();
        testCase.setId(this.id);
        testCase.setRequestValue(this.requestValue);
        testCase.setVerification(this.verification);
        testCase.setName(this.name);
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(interfaceInfoId);
        testCase.setInterfaceInfo(interfaceInfo);
        return testCase;
    }

    @Override
    public String toString() {
        return "TestCaseVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", interfaceInfoId=" + interfaceInfoId +
                ", requestValue='" + requestValue + '\'' +
                ", verification='" + verification + '\'' +
                '}';
    }
}
