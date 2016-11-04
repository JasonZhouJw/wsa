package com.alpha.web.controller;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.executor.WsdlAssembleExecutor;
import com.alpha.core.ws.utils.ILog;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ThirdJarController implements ILog {

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

    @PostMapping("/service/create")
    public String createService(@RequestParam UploadInfoVo uploadInfoVo, ModelMap model,
                                HttpServletRequest request, HttpServletResponse httpServletResponse) throws WebException {
        Response response = Response.init("thirdJar/jarView");
        ValidationUtils.validate(uploadInfoVo, response);
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
        this.servicesInfoService.save(servicesInfo);
        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
        return response.getView();
    }

}
