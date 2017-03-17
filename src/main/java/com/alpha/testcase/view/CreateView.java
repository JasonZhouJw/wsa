package com.alpha.testcase.view;

import com.alpha.common.view.BaseModelView;
import com.alpha.testcase.entities.TestCase;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.TEST_CASE_CREATE;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@Component("testCaseCreateView")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreateView extends BaseModelView {

    public CreateView() {
        this.setViewName(TEST_CASE_CREATE);
    }

    public void addTestCase(TestCase testCase) {
        this.addObject("testCase", testCase);
    }
}
