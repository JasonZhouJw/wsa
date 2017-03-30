package com.alpha.testcase.controller;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.page.PageView;
import com.alpha.common.utils.ValidationUtils;
import com.alpha.services.domain.ServicesInfos;
import com.alpha.testcase.domain.TestCaseImpl;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.CreateTestCaseVo;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.view.TestCaseCreateView;
import com.alpha.testcase.view.TestCaseIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Controller
public class TestCaseController {

    @Autowired
    private TestCaseImpl testCases;

    @Resource
    private ServicesInfos servicesInfos;

    @Autowired
    private PageView pageView;

    @Autowired
    @Qualifier("testCaseCreateView")
    private TestCaseCreateView testCaseCreateView;

    @Autowired
    @Qualifier("testCaseIndexView")
    private TestCaseIndexView testCaseIndexView;

    @GetMapping(TEST_CASE_INDEX)
    public ModelAndView index() {
        this.testCases.findAll(new TestCase(), pageView.create(), (page) -> {
            testCaseIndexView.addTestCase(page.getContent());
            pageView.display(page.getTotalPages());
        });
        return testCaseIndexView.Combine(pageView);
    }

    @GetMapping(TEST_CASE_TO_CREATE)
    public ModelAndView toCreate() {
        return testCaseCreateView;
    }

    @PostMapping(TEST_CASE_CREATE)
    public TestCaseCreateView create(CreateTestCaseVo testCaseVo) throws ValidationException, DataExistException, DataNotFoundException {
        ValidationUtils.validate(testCaseVo);
        testCaseCreateView.addTestCase(testCases.create(new TestCase(testCaseVo)));
        return testCaseCreateView;
    }

    @PostMapping(TEST_CASE_SEARCH)
    public ModelAndView search(TestCaseVo testCaseVo) {
        this.testCases.findAll(new TestCase(testCaseVo), pageView.create(), (page) -> {
            testCaseIndexView.addTestCase(page.getContent());
            testCaseIndexView.addSearchParam(testCaseVo);
            pageView.display(page.getTotalPages());
        });
        return testCaseIndexView.Combine(pageView);
    }

}
