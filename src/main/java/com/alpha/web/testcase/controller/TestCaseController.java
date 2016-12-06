package com.alpha.web.testcase.controller;

import com.alpha.core.cache.Caches;
import com.alpha.core.common.utils.BeanCopier;
import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.entity.TestCase;
import com.alpha.web.services.domain.InterfaceInfos;
import com.alpha.web.services.domain.ServicesInfos;
import com.alpha.web.services.model.InterfaceInfoVo;
import com.alpha.web.testcase.domain.TestCases;
import com.alpha.web.testcase.model.TestCaseVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-10-06.
 */
//@Controller
@RequestMapping("/TestCase")
public class TestCaseController {

    @Resource
    private InterfaceInfos interfaceInfos;

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
        model.addAttribute("interfaceList", InterfaceInfoVo.toVo(this.interfaceInfos.findAll()));
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

    public void initEdit(ModelMap modelMap) {
        Map<String, Object> servicesDataMap = Caches.SERVICES.get();
        Map<String, Map<String, InterfaceInfoVo>> servicesVoDataMap = new HashMap<String, Map<String, InterfaceInfoVo>>();
        servicesDataMap.forEach((key, dataValue) -> {
            servicesVoDataMap.put(key, new HashMap<String, InterfaceInfoVo>());
            Map<String, InterfaceInfo> value = (Map<String, InterfaceInfo>) dataValue;
            value.forEach((name, interfaceInfo) -> {
                servicesVoDataMap.get(key).put(name, InterfaceInfoVo.toVo(interfaceInfo));
            });
        });
        modelMap.addAttribute("servicesMap", servicesVoDataMap);
    }

    public void initView(ModelMap modelMap) {
        modelMap.addAttribute("demo", "demo");
    }
}
