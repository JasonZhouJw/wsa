package com.alpha.web.model;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.utils.enums.ResultType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-11-29.
 */
public class VerifyResultVo {

    private Long id;

    private Date executedTime = new Date();

    private Date updatedTime = new Date();

    private TestCaseVo testCase;

    private InterfaceInfoVo interfaceInfo;

    private ResultType result = ResultType.SUCCESS;

    private String message;

    public static VerifyResultVo toVo(VerifyResult verifyResult) {
        if (verifyResult == null) {
            return null;
        }
        VerifyResultVo vo = new VerifyResultVo();
        vo.setId(verifyResult.getId());
        vo.setMessage(verifyResult.getMessage());
        return vo;
    }

    public static List<VerifyResultVo> toVo(List<VerifyResult> verifyResultList) {
        List<VerifyResultVo> voList = new ArrayList<VerifyResultVo>();
        if (verifyResultList != null) {
            verifyResultList.forEach(verifyResult -> {
                VerifyResultVo vo = toVo(verifyResult);
                if (vo != null) {
                    voList.add(vo);
                }
            });
        }
        return voList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public TestCaseVo getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseVo testCase) {
        this.testCase = testCase;
    }

    public InterfaceInfoVo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfoVo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
