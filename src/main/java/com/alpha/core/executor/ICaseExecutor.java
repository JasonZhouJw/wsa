package com.alpha.core.executor;

import com.alpha.core.common.exceptions.CommonException;
import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.entity.TestCase;

/**
 * Created by jzhou237 on 9/9/2016.
 */
public interface ICaseExecutor {

    /**
     * initial the test case
     *
     * @param interfaceInfo
     */
    void init(InterfaceInfo interfaceInfo);

    /**
     * initial the test case
     *
     * @param testCase
     */
    void init(TestCase testCase);

    /**
     * send the execute and get response, execute the test case
     *
     * @throws CommonException
     */
    void execute() throws CommonException;

    /**
     * verify the response
     *
     * @param testCase
     * @param response
     */
    void verify(TestCase testCase, Object response);

    /**
     * finished job
     */
    void last();
}
