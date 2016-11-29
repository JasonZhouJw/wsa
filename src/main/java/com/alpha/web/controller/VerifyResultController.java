package com.alpha.web.controller;

import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.repository.search.SearchParam;
import com.alpha.web.model.VerifyResultVo;
import com.alpha.web.model.common.Pageable;
import com.alpha.web.services.IVerifyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jzhou237 on 2016-11-29.
 */
@RequestMapping("/VerifyResult")
@Controller
public class VerifyResultController {

    @Autowired
    private IVerifyResultService verifyResultService;

    @RequestMapping("/toView")
    public String toView(ModelMap modelMap) {
        PageRequest pageRequest = new PageRequest(0, 50);
        Page<VerifyResult> pageable = this.verifyResultService.search(new SearchParam(), pageRequest);
        modelMap.addAttribute("pageable", Pageable.toPageable(pageable));
        modelMap.addAttribute("verifyResultList", VerifyResultVo.toVo(pageable.getContent()));
        return "/VerifyResult/View";
    }
}
