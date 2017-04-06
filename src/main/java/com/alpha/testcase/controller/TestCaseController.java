package com.alpha.testcase.controller;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.page.PageView;
import com.alpha.common.utils.ValidationUtils;
import com.alpha.services.domain.ServicesInfos;
import com.alpha.testcase.domain.TestCaseImpl;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.CreateTestCaseVo;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.model.UpdateTestCaseVo;
import com.alpha.testcase.view.TestCaseCreateView;
import com.alpha.testcase.view.TestCaseExecuteView;
import com.alpha.testcase.view.TestCaseIndexView;
import com.alpha.testcase.view.TestCaseUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private TestCaseCreateView testCaseCreateView;

    @Autowired
    private TestCaseIndexView testCaseIndexView;

    @Autowired
    private TestCaseUpdateView testCaseUpdateView;

    @Autowired
    private TestCaseExecuteView testcaseExecuteView;

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
    public TestCaseCreateView create(CreateTestCaseVo testCaseVo) {
        try {
            ValidationUtils.validate(testCaseVo);
            testCases.create(TestCase.valueOf(testCaseVo), this.testCaseCreateView.getResultHandler());
        } catch (ValidationException e) {
            this.testCaseCreateView.getResultHandler().fail(testCaseVo, e.getMessages());
        }
        return testCaseCreateView;
    }

    @GetMapping(TEST_CASE_SEARCH)
    public ModelAndView search(TestCaseVo testCaseVo) {
        this.testCases.findAll(new TestCase(testCaseVo), pageView.create(), (page) -> {
            testCaseIndexView.addTestCase(page.getContent());
            testCaseIndexView.addSearchParam(testCaseVo);
            pageView.display(page.getTotalPages());
        });
        return testCaseIndexView.Combine(pageView);
    }


    @GetMapping(TEST_CASE_TO_UPDATE)
    public TestCaseUpdateView toUpdate(@PathVariable("id") Long id) {
        try {
            this.testCaseUpdateView.getResultHandler().success(this.testCases.findById(id));
        } catch (DataNotFoundException e) {
            this.testCaseUpdateView.getResultHandler().fail(null, e.getMessage());
        }
        return this.testCaseUpdateView;
    }

    @PostMapping(TEST_CASE_UPDATE)
    public TestCaseUpdateView update(UpdateTestCaseVo testCaseVo) {
        try {
            ValidationUtils.validate(testCaseVo);
            testCases.update(testCaseVo, this.testCaseUpdateView.getResultHandler());
        } catch (ValidationException e) {
            this.testCaseUpdateView.getResultHandler().fail(testCaseVo, e.getMessages());
        }
        return this.testCaseUpdateView;
    }

    @PostMapping
    public TestCaseExecuteView execute(UpdateTestCaseVo testCaseVo) {
        try {
            ValidationUtils.validate(testCaseVo);
            testCases.execute(testCaseVo, this.testcaseExecuteView.getResultHandler());
        } catch (ValidationException e) {
            this.testCaseUpdateView.getResultHandler().fail(testCaseVo, e.getMessages());
        } catch (Exception e) {
            this.testCaseUpdateView.getResultHandler().fail(testCaseVo, e.getMessage());
        }
        return this.testcaseExecuteView;
    }
}
