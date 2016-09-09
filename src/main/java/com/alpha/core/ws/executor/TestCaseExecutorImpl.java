package com.alpha.core.ws.executor;

/**
 * Created by jzhou237 on 9/9/2016.
 */
public class TestCaseExecutorImpl implements  ITestCaseExecutor{

    @Override
    public void init() {
        //TODO: 9/9/2016  get the wsdl data, propare the request data
    }

    @Override
    public void request() {
        //TODO: 9/9/2016  get the interface information and request the webservice, store the request data
        //TODO: 9/9/2016  it will support other request type, so request handler should be use the factory model
    }

    @Override
    public void response() {
        // TODO: 9/9/2016  store the response and reconstruct the response for validation
    }

    @Override
    public void verify() {
        // TODO: 9/9/2016 verify the response and store the result
    }

    @Override
    public void last() {
        // TODO: 9/9/2016 other work to do
    }
}
