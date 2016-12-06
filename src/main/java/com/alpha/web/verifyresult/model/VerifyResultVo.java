package com.alpha.web.verifyresult.model;

import com.alpha.core.common.utils.enums.ResultType;
import com.alpha.core.entity.VerifyResult;
import com.alpha.web.services.model.InterfaceInfoVo;
import com.alpha.web.testcase.model.TestCaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@Getter
@Setter
@ToString(exclude = {"testCase", "interfaceInfo"})
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

}
