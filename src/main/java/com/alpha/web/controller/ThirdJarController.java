package com.alpha.web.controller;

import com.alpha.core.ws.utils.ILog;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Controller
public class ThirdJarController implements ILog {

    @Value("${upload.jar.filePath}")
    private String filePath;

    @GetMapping("/toThirdJar")
    public String toThirdJarView() {
        return "thirdJar/jarView";
    }

    @PostMapping("/uploadThirdJar")
    public String upload(@RequestParam("serviceId") Long serviceId, @RequestParam("fileName") MultipartFile file, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
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
            }
        }
        return "thirdJar/jarView";
    }

    public String uploadNew(@RequestParam("service") String service, @RequestParam("fileName") MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        return "thirdJar/jarView";
    }
}
