package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.exception.CommonException;

/**
 * Created by jzhou237 on 9/9/2016.
 */
public interface ITestCaseExecutor {

    /**
     * init request
     */
    void init(InterfaceInfo interfaceInfo);

    /**
     * request
     */
    void request() throws CommonException;

    /**
     * response
     */
    void response();

    /**
     * init request test case
     */
    void verify();

    /**
     * last
     */
    void last();

}
