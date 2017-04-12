package com.alpha.services.controller;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.page.PageView;
import com.alpha.common.utils.ValidationUtils;
import com.alpha.services.domain.IServiceInfo;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoCreateVo;
import com.alpha.services.model.ServiceInfoUpdateVo;
import com.alpha.services.threads.AssembleWsdlRunnable;
import com.alpha.services.view.ServiceInfoCreateView;
import com.alpha.services.view.ServiceInfoIndexView;
import com.alpha.services.view.ServiceInfoUpdateView;
import com.alpha.wsdl2java.Wsdl2JavaGenerator;
import com.alpha.wsdl2java.WsdlClassCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Controller
public class ServicesInfoController {

    @Value("${upload.jar.filePath}")
    private String filePath;

    @Autowired
    private WsdlClassCompiler wsdlClassCompiler;

    @Autowired
    private Wsdl2JavaGenerator wsdl2JavaGenerator;

    @Autowired
    private IServiceInfo servicesInfo;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private PageView pageView;

    @Autowired
    private ServiceInfoIndexView serviceInfoIndexView;

    @Autowired
    private ServiceInfoCreateView servicesInfoCreateView;

    @Autowired
    private ServiceInfoUpdateView serviceInfoUpdateView;

    @GetMapping(SERVICE_INFO_INDEX)
    public ModelAndView index() {
        ServiceInfo condition = new ServiceInfo();
        condition.setWsdl2java(null);
        servicesInfo.findAll(condition, pageView.create(new Sort(Sort.Direction.DESC, "id")), (page) -> {
            serviceInfoIndexView.setServicesInfo(page.getContent());
            serviceInfoIndexView.setSearchCondition(condition);
            pageView.display(page.getTotalPages());
        });
        return this.serviceInfoIndexView.Combine(pageView);
    }

    @GetMapping(SERVICE_INFO_TO_CREATE)
    public ModelAndView toCreate() {
        return servicesInfoCreateView;
    }

    @PostMapping(SERVICE_INFO_CREATE)
    public ModelAndView create(ServiceInfoCreateVo serviceInfoVo) {
        try {
            ValidationUtils.validate(serviceInfoVo);
            this.servicesInfo.create(serviceInfoVo, this.servicesInfoCreateView.getResultHandler());
        } catch (ValidationException e) {
            servicesInfoCreateView.getResultHandler().fail(serviceInfoVo, e.getMessages());
        }
        return servicesInfoCreateView;
    }

    @PostMapping(SERVICE_INFO_CREATE_ASSEMBLE)
    public ModelAndView createAndAssemble(ServiceInfoCreateVo serviceInfoVo) {
        try {
            ValidationUtils.validate(serviceInfoVo);
            this.servicesInfo.create(serviceInfoVo, this.servicesInfoCreateView.getResultHandler());
        } catch (ValidationException e) {
            servicesInfoCreateView.getResultHandler().fail(serviceInfoVo, e.getMessages());
        }
        if (servicesInfoCreateView.getResultHandler().isSuccess()) {
            executorService.execute(new AssembleWsdlRunnable(servicesInfoCreateView.getResultHandler().getSuccessObj(), wsdlClassCompiler, wsdl2JavaGenerator, servicesInfo));
        }
        return servicesInfoCreateView;
    }

    @GetMapping(SERVICE_INFO_TO_UPDATE + "/{id}")
    public ModelAndView toUpdate(@PathVariable("id") Long id) {
        try {
            this.serviceInfoUpdateView.getResultHandler().successNoMsg(this.servicesInfo.findOne(id));
        } catch (DataNotFoundException e) {
            this.serviceInfoUpdateView.getResultHandler().fail(null, e.getMessage());
        }
        return this.serviceInfoUpdateView;
    }

    @PostMapping(SERVICE_INFO_UPDATE)
    public ModelAndView update(ServiceInfoUpdateVo serviceInfoVo) {
        try {
            ValidationUtils.validate(serviceInfoVo);
            this.servicesInfo.update(serviceInfoVo, this.serviceInfoUpdateView.getResultHandler());
        } catch (ValidationException e) {
            serviceInfoUpdateView.getResultHandler().fail(serviceInfoVo, e.getMessages());
        }
        return this.serviceInfoUpdateView;
    }

    @PostMapping(SERVICE_INFO_ASSEMBLE)
    public ModelAndView assemble(ServiceInfoUpdateVo serviceInfoVo) {
        try {
            ValidationUtils.validate(serviceInfoVo);
            ServiceInfo serviceInfo = this.servicesInfo.findOne(serviceInfoVo.getId());
            executorService.execute(new AssembleWsdlRunnable(serviceInfo, wsdlClassCompiler, wsdl2JavaGenerator, servicesInfo));
            this.serviceInfoUpdateView.getResultHandler().success(serviceInfo);
        } catch (ValidationException e) {
            serviceInfoUpdateView.getResultHandler().fail(serviceInfoVo, e.getMessages());
        } catch (DataNotFoundException e) {
            serviceInfoUpdateView.getResultHandler().fail(serviceInfoVo, e.getMessage());
        }
        return this.serviceInfoUpdateView;
    }
}
