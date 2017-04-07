package com.alpha.services.controller;

import com.alpha.common.page.PageView;
import com.alpha.services.domain.IServicesInfo;
import com.alpha.services.entities.ServicesInfo;
import com.alpha.services.view.ServicesInfoIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alpha.common.controller.Urls.SERVICES_INFO_INDEX;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Controller
public class ServicesInfoController {

    @Value("${upload.jar.filePath}")
    private String filePath;

//    @Resource
//    private WsdlAssembleExecutor wsdlAssembleExecutor;

    @Autowired
    private IServicesInfo servicesInfo;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private PageView pageView;

    @Autowired
    private ServicesInfoIndexView servicesInfoIndexView;

    @GetMapping(SERVICES_INFO_INDEX)
    public ModelAndView index() {
        ServicesInfo condition = new ServicesInfo();
        servicesInfo.findAll(new ServicesInfo(), pageView.create(), (page) -> {
            servicesInfoIndexView.setServicesInfo(page.getContent());
            servicesInfoIndexView.setSearchCondition(condition);
            pageView.display(page.getTotalPages());
        });
        return this.servicesInfoIndexView.Combine(pageView);
    }
//
//    //    @GetMapping("/toThirdJar")
//    public String toThirdJarView() {
//        return "thirdJar/jarView";
//    }
//
//    //    @GetMapping("/toCreate")
//    public String toCreateView(ModelMap modelMap) {
//        return "servicesInfo/create";
//    }
//
//    //    @PostMapping("/uploadThirdJar/{id}")
//    public String doUpload(@PathVariable("id") Long id, @RequestParam MultipartFile file, ModelMap model) throws WebException {
////        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
////        if (!file.isEmpty()) {
////            String type = file.getOriginalFilename().substring(
////                    file.getOriginalFilename().lastIndexOf("."));
////            if (StringUtils.equalsIgnoreCase(type, ".jar")) {
////                String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
////                String path = filePath + fileName;
////                File destFile = new File(path);
////                try {
////                    FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
////                } catch (IOException e) {
////                    throw new WebException(e.getMessage());
////                }
////                servicesInfo.setPath(path);
////                this.servicesInfos.create(servicesInfo);
////                executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
////            }
////        }
//        return "thirdJar/jarView";
//    }
//
//    //    @PostMapping("/create")
//    public String doCreate(@ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model,
//                           HttpServletRequest request, HttpServletResponse httpServletResponse) throws WebException {
////        ValidationUtils.validate(uploadInfoVo, response);
////        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
////            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
////        }
////        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
////                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
////        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
////            throw new WebException(response.addValidationError("File type is invalidated"));
////        }
////        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
////        String path = filePath + fileName;
////        File destFile = new File(path);
////        try {
////            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
////        } catch (IOException e) {
////            throw new WebException(response.addDanger(e.getMessage()));
////        }
////        ServicesInfo servicesInfo = new ServicesInfo();
////        servicesInfo.setService(uploadInfoVo.getService());
////        servicesInfo.setPath(path);
////        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
////        servicesInfo.setType(uploadInfoVo.getEnvType());
////        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
////        ServicesInfo savedServicesInfo = this.servicesInfos.create(servicesInfo);
////        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
////        return "servicesInfo/create";
//        return null;
//    }
//
//    //    @PostMapping("update/{id}")
//    public String doUpdate(@PathVariable("id") Long id, @ModelAttribute UploadInfoVo uploadInfoVo, ModelMap model) throws WebException {
////        Response response = Response.init("servicesInfo/update");
////        ValidationUtils.validate(uploadInfoVo, response);
////        if (uploadInfoVo.getFile() == null || uploadInfoVo.getFile().isEmpty()) {
////            throw new WebException(response.addValidationError(Errors.FILE_NOT_FOUND.getMessage()));
////        }
////        String type = uploadInfoVo.getFile().getOriginalFilename().substring(
////                uploadInfoVo.getFile().getOriginalFilename().lastIndexOf("."));
////        if (!StringUtils.equalsIgnoreCase(type, ".jar")) {
////            throw new WebException(response.addValidationError("File type is invalidated"));
////        }
////        String fileName = uploadInfoVo.getFile().getOriginalFilename().substring(uploadInfoVo.getFile().getOriginalFilename().lastIndexOf(".")) + "_" + System.currentTimeMillis() + type;
////        String path = filePath + fileName;
////        File destFile = new File(path);
////        try {
////            FileUtils.copyInputStreamToFile(uploadInfoVo.getFile().getInputStream(), destFile);
////        } catch (IOException e) {
////            throw new WebException(response.addDanger(e.getMessage()));
////        }
////        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
////        if (servicesInfo == null) {
////            throw new WebException("Object is not found.");
////        }
////        servicesInfo.setPath(path);
////        servicesInfo.setProtocolType(uploadInfoVo.getProtocolType());
////        servicesInfo.setType(uploadInfoVo.getEnvType());
////        servicesInfo.setAliasName(uploadInfoVo.getAliasName());
////        ServicesInfo savedServicesInfo = this.servicesInfos.update(servicesInfo);
////        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, savedServicesInfo));
////        return response.getView();
//        return null;
//    }
//
//    //    @GetMapping("refresh/{id}")
//    public String doRefresh(@PathVariable("id") Long id, ModelMap model) throws WebException {
////        Response response = Response.init("servicesInfo/update");
////        ServicesInfo servicesInfo = this.servicesInfos.findById(id);
////        if (servicesInfo == null) {
////            throw new WebException("Object is not found.");
////        }
////        executorService.execute(new AssembleWsdlRunnable(this.wsdlAssembleExecutor, servicesInfo));
////        return response.getView();
//        return null;
//    }
//
//    public void initEdit(ModelMap modelMap) {
//        modelMap.addAttribute("protocolTypes", ProtocolType.values());
//    }
}
