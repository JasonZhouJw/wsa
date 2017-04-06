package com.alpha.testcase.view;

import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.TEST_CASE_EXECUTE;

/**
 * Created by jzhou237 on 2017-04-06.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestCaseExecuteView extends HandlerModelView<TestCase, TestCaseVo> {
    private String startMessage;

    public TestCaseExecuteView(@Value("label.test.case.execute.start") String startMessage) {
        this.startMessage = startMessage;
        this.setViewName(TEST_CASE_EXECUTE);
    }

    public void success(Result<TestCase> result) {
        this.addMessage(startMessage);
        this.addTestCase(result.getResult());
    }

    public void fail(Result<TestCaseVo> result) {
        this.setMessage(result.getMessageList());
        this.addObject("testCase", result.getResult());
    }

    public void addTestCase(TestCase testCase) {
        this.addObject("testCase", testCase.toVo());
    }

}
