package com.alpha.testcase.controller;

import com.alpha.testcase.domain.ICaseGroup;
import com.alpha.testcase.view.CaseGroupIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.CASE_GROUP_INDEX;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Controller
public class CaseGroupController {

    @Autowired
    private ICaseGroup caseGroup;

    @Autowired
    private CaseGroupIndex caseGroupIndex;

    @GetMapping(CASE_GROUP_INDEX)
    public ModelAndView index() {
        caseGroupIndex.setCaseGroup(this.caseGroup.findAll());
        return caseGroupIndex;
    }

}
