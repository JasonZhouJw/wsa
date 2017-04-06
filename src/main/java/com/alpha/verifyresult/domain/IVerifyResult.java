package com.alpha.verifyresult.domain;

import com.alpha.verifyresult.entities.VerifyResult;

import java.util.Map;

/**
 * Created by jzhou237 on 2017-04-06.
 */
public interface IVerifyResult {

    /**
     * save the result after executing test case.
     * <p>
     * <b>Transaction type is nested.</b>
     *
     * @param resultMap
     */
    void saveResult(Map<String, VerifyResult> resultMap);
}
