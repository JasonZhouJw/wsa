package com.alpha.web.controller;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.executor.WsdlAssembleExecutor;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.enums.ProtocolType;
import com.alpha.web.services.IServicesInfoService;
import com.alpha.web.threads.AssembleWsdlRunnable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // TODO: 2016-09-30 should modify the parameters, and parameters should have other data
    @PostMapping("/uploadThirdJar")
    public String upload(@RequestParam("serviceId") Long serviceId, @RequestParam("fileName") MultipartFile file, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        ServicesInfo servicesInfo = this.servicesInfoService.findById(serviceId);
        if (servicesInfo == null) {
            // TODO: 2016-09-30 add error message for that
            return "thirdJar/jarView";
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
                    LOGGER.error(e.getMessage(), e);
                }
                servicesInfo.setPath(path);
                this.servicesInfoService.save(servicesInfo);
                executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
            }

        }

        return "thirdJar/jarView";
    }

    // TODO: 2016-09-30 modify the parameters
    public String uploadNew(@RequestParam("service") String service, @RequestParam("fileName") MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
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
                    LOGGER.error(e.getMessage(), e);
                }
                ServicesInfo servicesInfo = new ServicesInfo();
                servicesInfo.setService(service);
                servicesInfo.setPath(path);
                servicesInfo.setProtocolType(ProtocolType.WEB_SERVICE);
                this.servicesInfoService.save(servicesInfo);
                executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
            }

        }
        return "thirdJar/jarView";
    }

}
