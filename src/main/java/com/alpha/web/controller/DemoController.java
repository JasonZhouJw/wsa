package com.alpha.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jzhou237 on 2016-09-21.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/toDemo")
    public String toDemo() {
        return "Demo";
    }
}
