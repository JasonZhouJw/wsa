package com.alpha.web.controller;

import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.utils.BeanCopier;
import com.alpha.web.model.InterfaceInfoVo;
import com.alpha.web.model.TestCaseVo;
import com.alpha.web.services.IInterfaceInfoService;
import com.alpha.web.services.IServicesInfoService;
import com.alpha.web.services.ITestCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Controller
@RequestMapping("/TestCase")
public class TestCaseController {

    @Resource
    private IInterfaceInfoService interfaceInfoService;

    @Resource
    private ITestCaseService testCaseService;

    @Resource
    private IServicesInfoService servicesInfoService;

    @RequestMapping("/toEditTestCase/{id}")
    public String toEditView(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("interfaceList", InterfaceInfoVo.toVo(this.interfaceInfoService.findAll()));
        model.addAttribute("testCase", TestCaseVo.toVo(this.testCaseService.findById(id)));
        return "/TestCase/EditCase";
    }

    @RequestMapping("/toAddTestCase")
    public String toAddView(ModelMap model) {
        model.addAttribute("interfaceList", InterfaceInfoVo.toVo(this.interfaceInfoService.findAll()));
        return "/TestCase/AddCase";
    }

    @RequestMapping("/toView")
    public String toView(ModelMap model) {
        List<TestCaseVo> testCaseVoList = BeanCopier.copyBean(this.testCaseService.findAllActive(), TestCaseVo.class);
        if (testCaseVoList.isEmpty()) {
            TestCaseVo testCaseVo = new TestCaseVo();
            testCaseVo.setRequestValue("request value");
            testCaseVo.setVerification("verification");
            testCaseVo.setName("name");
            testCaseVo.setId(10L);
            testCaseVoList.add(testCaseVo);
        }
        model.addAttribute("testCaseList", testCaseVoList);
        return "/TestCase/View";
    }

    @RequestMapping("/addTestCase")
    public String addTestCase(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCaseService.save(testCase);
        }
        return "redirect:toAddTestCase";
    }

    @RequestMapping("/editTestCase")
    public String editTestCase(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCaseService.save(testCase);
        }
        return "redirect:toEditTestCase";
    }

    @RequestMapping("/execute")
    public String execute(@PathVariable("id") Long id, ModelMap model) {
        TestCase testCase = this.testCaseService.findById(id);
        return "redirect:toEditTestCase";
    }
}
