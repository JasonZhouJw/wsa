package com.alpha.web.controller;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.executor.WsdlAssembleExecutor;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.enums.EnvType;
import com.alpha.core.ws.utils.enums.Errors;
import com.alpha.core.ws.utils.enums.ProtocolType;
import com.alpha.web.exceptions.WebException;
import com.alpha.web.model.UploadInfoVo;
import com.alpha.web.model.common.Response;
import com.alpha.web.services.IServicesInfoService;
import com.alpha.web.threads.AssembleWsdlRunnable;
import com.alpha.web.utils.ResponseType;
import com.alpha.web.utils.ValidationUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Controller
@RequestMapping("/ServicesInfo")
public class ServicesInfoController implements ILog {

    @Value("${upload.jar.filePath}")
    private String filePath;

    @Resource
    private WsdlAssembleExecutor wsdlAssembleExecutor;

    @Resource
    private IServicesInfoService servicesInfoService;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GetMapping("/toThirdJar")
    public String toThirdJarView() {
        return "thirdJar/jarView";
    }

    @GetMapping("/toCreate")
    public String toCreateView(ModelMap modelMap) {
        modelMap.addAttribute("envTypes", EnvType.values());
        modelMap.addAttribute("protocolTypes", ProtocolType.values());
        return "servicesInfo/create";
    }

    @PostMapping("/uploadThirdJar/{id}")
    public String upload(@PathVariable("id") Long id, @RequestParam MultipartFile file, ModelMap model) throws WebException {
        Response response = Response.init("thirdJar/jarView");
        ServicesInfo servicesInfo = this.servicesInfoService.findById(id);
        if (servicesInfo == null) {
            throw new WebException(response.changeStatus(ResponseType.ELEMENTS_NOT_FOUND));
        }
        if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf("."));
            if (StringUtils.equalsIgnoreCase(type, ".jar")) {
                String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
                String path = filePath + fileName;
                File destFile = new File(path);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
                } catch (IOException e) {
                    throw new WebException(response.addError(e.getMessage()));
                }
                servicesInfo.setPath(path);
                this.servicesInfoService.save(servicesInfo);
                executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
            }
        }
        return "thirdJar/jarView";
    }

    @PostMapping("/create")
    public String createService(@ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model,
                                HttpServletRequest request, HttpServletResponse httpServletResponse) throws WebException {
        Response response = Response.init("servicesInfo/create");
        ValidationUtils.validate(uploadInfoVo, response);
        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
        }
        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
            throw new WebException(response.addValidationError("File type is invalidated"));
        }
        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
        String path = filePath + fileName;
        File destFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
        } catch (IOException e) {
            throw new WebException(response.addError(e.getMessage()));
        }
        ServicesInfo servicesInfo = new ServicesInfo();
        servicesInfo.setService(uploadInfoVo.getService());
        servicesInfo.setPath(path);
        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
        servicesInfo.setType(uploadInfoVo.getEnvType());
        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
        ServicesInfo savedServicesInfo = this.servicesInfoService.save(servicesInfo);
        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
        return response.getView();
    }

    @PostMapping("update/{id}")
    public String updateCreate(@PathVariable("id") Long id, @ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model) throws WebException {
        Response response = Response.init("servicesInfo/update");
        ValidationUtils.validate(uploadInfoVo, response);
        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
        }
        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
            throw new WebException(response.addValidationError("File type is invalidated"));
        }
        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
        String path = filePath + fileName;
        File destFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
        } catch (IOException e) {
            throw new WebException(response.addError(e.getMessage()));
        }
        ServicesInfo servicesInfo = this.servicesInfoService.findById(id);
        if (servicesInfo == null) {
            throw new WebException("Object is not found.");
        }
        servicesInfo.setPath(path);
        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
        servicesInfo.setType(uploadInfoVo.getEnvType());
        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
        ServicesInfo savedServicesInfo = this.servicesInfoService.update(servicesInfo);
        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
        return response.getView();
    }

}
