package com.alpha.web.controller;

import com.alpha.core.ws.utils.ILog;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by jzhou237 on 2016-09-23.
 */
@Controller
public class ThirdJarController implements ILog {

    @GetMapping("/toThirdJar")
    public String toThirdJarView() {
        return "thirdJar/jarView";
    }

    @PostMapping("/uploadThirdJar")
    public String upload(@RequestParam("fileName") CommonsMultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));
            String filename = System.currentTimeMillis() + type;
            String path = "c:/tmp/" + filename;
            File destFile = new File(path);
            try {
                FileUtils
                        .copyInputStreamToFile(file.getInputStream(), destFile);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return "thirdJar/jarView";
    }
}
