package com.alpha.services.controller;

import com.alpha.common.enums.ProtocolType;
import com.alpha.common.exceptions.WebException;
import com.alpha.services.domain.ServicesInfos;
import com.alpha.services.model.UploadInfoVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jzhou237 on 2016-09-23.
 */
//@Controller
//@RequestMapping("/ServicesInfo")
public class ServicesInfoController {

    @Value("${upload.jar.filePath}")
    private String filePath;

//    @Resource
//    private WsdlAssembleExecutor wsdlAssembleExecutor;

    @Resource
    private ServicesInfos servicesInfos;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    //    @GetMapping("/toThirdJar")
    public String toThirdJarView() {
        return "thirdJar/jarView";
    }

    //    @GetMapping("/toCreate")
    public String toCreateView(ModelMap modelMap) {
        return "servicesInfo/create";
    }

    //    @PostMapping("/uploadThirdJar/{id}")
    public String doUpload(@PathVariable("id") Long id, @RequestParam MultipartFile file, ModelMap model) throws WebException {
//        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
//        if (!file.isEmpty()) {
//            String type = file.getOriginalFilename().substring(
//                    file.getOriginalFilename().lastIndexOf("."));
//            if (StringUtils.equalsIgnoreCase(type, ".jar")) {
//                String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
//                String path = filePath + fileName;
//                File destFile = new File(path);
//                try {
//                    FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
//                } catch (IOException e) {
//                    throw new WebException(e.getMessage());
//                }
//                servicesInfo.setPath(path);
//                this.servicesInfos.create(servicesInfo);
//                executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
//            }
//        }
        return "thirdJar/jarView";
    }

    //    @PostMapping("/create")
    public String doCreate(@ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model,
                           HttpServletRequest request, HttpServletResponse httpServletResponse) throws WebException {
//        ValidationUtils.validate(uploadInfoVo, response);
//        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
//            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
//        }
//        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
//                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
//        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
//            throw new WebException(response.addValidationError("File type is invalidated"));
//        }
//        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
//        String path = filePath + fileName;
//        File destFile = new File(path);
//        try {
//            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
//        } catch (IOException e) {
//            throw new WebException(response.addDanger(e.getMessage()));
//        }
//        ServicesInfo servicesInfo = new ServicesInfo();
//        servicesInfo.setService(uploadInfoVo.getService());
//        servicesInfo.setPath(path);
//        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
//        servicesInfo.setType(uploadInfoVo.getEnvType());
//        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
//        ServicesInfo savedServicesInfo = this.servicesInfos.create(servicesInfo);
//        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
//        return "servicesInfo/create";
        return null;
    }

    //    @PostMapping("update/{id}")
    public String doUpdate(@PathVariable("id") Long id, @ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model) throws WebException {
//        Response response = Response.init("servicesInfo/update");
//        ValidationUtils.validate(uploadInfoVo, response);
//        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
//            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
//        }
//        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
//                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
//        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
//            throw new WebException(response.addValidationError("File type is invalidated"));
//        }
//        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
//        String path = filePath + fileName;
//        File destFile = new File(path);
//        try {
//            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
//        } catch (IOException e) {
//            throw new WebException(response.addDanger(e.getMessage()));
//        }
//        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
//        if (servicesInfo == null) {
//            throw new WebException("Object is not found.");
//        }
//        servicesInfo.setPath(path);
//        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
//        servicesInfo.setType(uploadInfoVo.getEnvType());
//        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
//        ServicesInfo savedServicesInfo = this.servicesInfos.update(servicesInfo);
//        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
//        return response.getView();
        return null;
    }

    //    @GetMapping("refresh/{id}")
    public String doRefresh(@PathVariable("id") Long id, ModelMap model) throws WebException {
//        Response response = Response.init("servicesInfo/update");
//        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
//        if (servicesInfo == null) {
//            throw new WebException("Object is not found.");
//        }
//        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
//        return response.getView();
        return null;
    }

    public void initEdit(ModelMap modelMap) {
        modelMap.addAttribute("protocolTypes", ProtocolType.values());
    }
}
