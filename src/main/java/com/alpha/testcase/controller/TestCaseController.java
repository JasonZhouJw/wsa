package com.alpha.testcase.controller;

import com.alpha.common.utils.BeanCopier;
import com.alpha.services.domain.ServicesInfos;
import com.alpha.testcase.domain.TestCases;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jzhou237 on 2016-10-06.
 */
//@Controller
@RequestMapping("/TestCase")
public class TestCaseController {

    @Resource
    private TestCases testCases;

    @Resource
    private ServicesInfos servicesInfos;

    @RequestMapping("/toEditTestCase/{id}")
    public String toEditView(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("testCase", TestCaseVo.toVo(this.testCases.findById(id)));
        return "/TestCase/EditCase";
    }

    @RequestMapping("/toAddTestCase")
    public String toAddView(ModelMap model) {
//        model.addAttribute("interfaceList", InterfaceInfoVo.toVo(this.interfaceInfos.findAll()));
        return "/TestCase/AddCase";
    }

    @RequestMapping("/toView")
    public String toView(ModelMap model) {
        model.addAttribute("testCaseList", BeanCopier.copyBean(this.testCases.findAllActive(), TestCaseVo.class));
        return "/TestCase/View";
    }

    @RequestMapping("/addTestCase")
    public String doAdd(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCases.save(testCase);
        }
        return "redirect:toAddTestCase";
    }

    @RequestMapping("/editTestCase")
    public String doEdit(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCases.save(testCase);
        }
        return "redirect:toEditTestCase";
    }

    @RequestMapping("/execute")
    public String doExecute(@PathVariable("id") Long id, ModelMap model) {
        TestCase testCase = this.testCases.findById(id);
        return "redirect:toEditTestCase/" + id;
    }


    public void initView(ModelMap modelMap) {
        modelMap.addAttribute("demo", "demo");
    }
}
