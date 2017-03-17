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
import com.alpha.testcase.view.CreateView;
import com.alpha.testcase.view.IndexView;
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
    private CreateView createView;

    @Autowired
    @Qualifier("testCaseIndexView")
    private IndexView indexView;

    @GetMapping(TEST_CASE_INDEX)
    public ModelAndView index() {
        this.testCases.findAll(new TestCase(), pageView.create(), (page) -> {
            indexView.addTestCase(page.getContent());
            pageView.display(page.getTotalPages());
        });
        return indexView.Combine(pageView);
    }

    @GetMapping(TEST_CASE_TO_CREATE)
    public ModelAndView toCreate() {
        // TODO: 2017-03-17 add method drop down and group
        return createView;
    }

    @PostMapping(TEST_CASE_CREATE)
    public CreateView create(CreateTestCaseVo testCaseVo) throws ValidationException, DataExistException, DataNotFoundException {
        ValidationUtils.validate(testCaseVo);
        createView.addTestCase(testCases.create(new TestCase(testCaseVo)));
        return createView;
    }

    @PostMapping(TEST_CASE_SEARCH)
    public ModelAndView search(TestCaseVo testCaseVo) {
        this.testCases.findAll(new TestCase(testCaseVo), pageView.create(), (page) -> {
            indexView.addTestCase(page.getContent());
            pageView.display(page.getTotalPages());
            indexView.addObject("searchParam", testCaseVo);
        });
        return indexView.Combine(pageView);
    }

}
