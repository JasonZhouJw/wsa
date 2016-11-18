package com.alpha.web.controller;

import com.alpha.core.ws.cache.Caches;
import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.utils.BeanCopier;
import com.alpha.web.controller.common.BaseEditController;
import com.alpha.web.controller.common.BaseViewController;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Controller
@RequestMapping("/TestCase")
public class TestCaseController implements BaseEditController, BaseViewController {

    @Resource
    private IInterfaceInfoService interfaceInfoService;

    @Resource
    private ITestCaseService testCaseService;

    @Resource
    private IServicesInfoService servicesInfoService;

    @RequestMapping("/toEditTestCase/{id}")
    public String toEditView(@PathVariable("id") Long id, ModelMap model) {
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
        model.addAttribute("testCaseList", BeanCopier.copyBean(this.testCaseService.findAllActive(), TestCaseVo.class));
        return "/TestCase/View";
    }

    @RequestMapping("/addTestCase")
    public String doAdd(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCaseService.save(testCase);
        }
        return "redirect:toAddTestCase";
    }

    @RequestMapping("/editTestCase")
    public String doEdit(TestCaseVo testCaseVo, HttpServletResponse response, HttpServletRequest request) {
        if (testCaseVo != null) {
            TestCase testCase = testCaseVo.toDo();
            this.testCaseService.save(testCase);
        }
        return "redirect:toEditTestCase";
    }

    @RequestMapping("/execute")
    public String doExecute(@PathVariable("id") Long id, ModelMap model) {
        TestCase testCase = this.testCaseService.findById(id);
        return "redirect:toEditTestCase/" + id;
    }

    @Override
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

    @Override
    public void initView(ModelMap modelMap) {
        modelMap.addAttribute("demo", "demo");
    }
}
