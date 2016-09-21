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
    public void init(InterfaceInfo interfaceInfo);

    /**
     * send the request and get response, execute the test case
     *
     * @throws CommonException
     */
    public void execute() throws CommonException;

    /**
     * verify the response
     *
     * @param testCase
     * @param response
     */
    public void verify(TestCase testCase, Object response);

    /**
     * finished job
     */
    public void last();
}
