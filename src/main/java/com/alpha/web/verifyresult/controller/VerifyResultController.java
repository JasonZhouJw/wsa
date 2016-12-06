package com.alpha.web.verifyresult.controller;

import com.alpha.web.home.view.HomeView;
import com.alpha.web.verifyresult.domain.VerifyResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jzhou237 on 2016-11-29.
 */
//@RequestMapping("/VerifyResult")
//@Controller
public class VerifyResultController {

    @Autowired
    private HomeView homeView;

    @Autowired
    private VerifyResults verifyResults;

    @GetMapping("/toView")
    public ModelAndView toView() {
        return homeView;
    }

//    @RequestMapping("/toView")
//    public String toView(ModelMap modelMap) {
//        PageRequest pageRequest = new PageRequest(0, 50);
//        Page<VerifyResult> pageable = this.verifyResultService.search(new SearchParam(), pageRequest);
//        modelMap.addAttribute("pageable", Pageable.toPageable(pageable));
//        modelMap.addAttribute("verifyResultList", VerifyResultVo.toVo(pageable.getContent()));
//        return "/VerifyResult/View";
//    }
}
