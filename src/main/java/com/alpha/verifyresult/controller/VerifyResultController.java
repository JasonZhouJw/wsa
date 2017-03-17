package com.alpha.verifyresult.controller;

import com.alpha.home.view.HomeView;
import com.alpha.verifyresult.domain.VerifyResults;
import com.alpha.verifyresult.view.IndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@Controller
public class VerifyResultController {

    @Autowired
    private HomeView homeView;

    @Autowired
    @Qualifier("verifyResultIndexView")
    private IndexView indexView;

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
