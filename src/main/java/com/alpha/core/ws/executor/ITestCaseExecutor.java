package com.alpha.core.ws.executor;

/**
 * Created by jzhou237 on 9/9/2016.
 */
public interface ITestCaseExecutor {

    /**
     * init request
     */
    public void init();

    /**
     * request
     */
    public void request();

    /**
     * response
     */
    public void response();

    /**
     * init request test case
     */
    public void verify();

    /**
     * last
     */
    public void last();

}
