package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.exception.CommonException;

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
