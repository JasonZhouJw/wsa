package com.alpha.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jzhou237 on 2016-09-21.
 */
@Controller
public class DemoController {

    @GetMapping("/toDemo")
    public String greeting() {
        System.out.println("in");
        return "demo/demo";
    }
}
