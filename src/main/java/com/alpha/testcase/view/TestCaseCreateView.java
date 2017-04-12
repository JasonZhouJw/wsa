package com.alpha.testcase.view;

import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.services.domain.IServiceInfo;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.testcase.domain.ICaseGroup;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.TEST_CASE_CREATE;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@Setter
@Getter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestCaseCreateView extends HandlerModelView<TestCase, TestCaseVo> {

    private final IServiceInfo servicesInfo;

    private final ICaseGroup caseGroup;

    @Autowired
    public TestCaseCreateView(IServiceInfo servicesInfo, ICaseGroup caseGroup) {
        this.servicesInfo = servicesInfo;
        this.caseGroup = caseGroup;
        this.setViewName(TEST_CASE_CREATE);
        this.init();
    }

    public void addTestCase(TestCase testCase) {
        this.addObject("testCase", testCase.toVo());
    }

    public void init() {
        this.addObject("servicesInfoList", ServiceInfo.toVo(this.servicesInfo.findAllActive(), true));
        this.addObject("caseGroupList", CaseGroup.toVo(this.caseGroup.findAllActive()));
    }

    public void success(Result<TestCase> result) {
        super.success();
        this.addTestCase(result.getResult());
    }

    public void fail(Result<TestCaseVo> result) {
        this.setMessage(result.getMessageList());
        this.addObject("testCase", result.getResult());
    }
}
