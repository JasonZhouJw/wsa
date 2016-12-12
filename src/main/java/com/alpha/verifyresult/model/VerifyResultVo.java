package com.alpha.verifyresult.model;

import com.alpha.common.enums.ResultType;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.verifyresult.entities.VerifyResult;
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
@ToString(exclude = {"testCase"})
public class VerifyResultVo {

    private Long id;

    private Date executedTime = new Date();

    private Date updatedTime = new Date();

    private TestCaseVo testCase;

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
