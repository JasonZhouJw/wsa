package com.alpha.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jzhou237 on 2016-09-28.
 */
@Controller
@RequestMapping("/cases")
public class CasesController {

    @RequestMapping("/toAddCaseView")
    public String toEditView() {
        return "cases/addCase";
    }

    @RequestMapping("/addCase")
    public String addCase(@RequestParam("input") String input, @RequestParam("verify") String verify, HttpServletRequest
            request, HttpServletResponse response, Model model) {
//        Yaml yaml = new Yaml();
//        JarInfo jarinfo = yaml.loadAs(input, JarInfo.class);
//        System.out.println(jarinfo);
//        YamlReader reader = new YamlReader(input);
//        try {
//            JarInfo jarinfo = reader.read(JarInfo.class);
//            System.out.println(jarinfo);
//        } catch (YamlException e) {
//            e.printStackTrace();
//        }
//        JarInfo jarinfo = (JarInfo) Yaml.loadType(input, JarInfo.class);
//        System.out.println(jarinfo);
        return "cases/addCase";
    }
}
