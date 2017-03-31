package com.alpha.testcase.controller;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.utils.ValidationUtils;
import com.alpha.testcase.domain.ICaseGroup;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CreateCaseGroupVo;
import com.alpha.testcase.view.CaseGroupCreateView;
import com.alpha.testcase.view.CaseGroupIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Controller
public class CaseGroupController {

    @Autowired
    private ICaseGroup caseGroup;

    @Autowired
    private CaseGroupIndex caseGroupIndex;

    @Autowired
    private CaseGroupCreateView caseGroupCreateView;

    @GetMapping(CASE_GROUP_INDEX)
    public ModelAndView index() {
        caseGroupIndex.setCaseGroup(this.caseGroup.findAll());
        return caseGroupIndex;
    }

    @GetMapping(CASE_GROUP_TO_CREATE)
    public CaseGroupCreateView toCreate() {
        return caseGroupCreateView;
    }

    @PostMapping(CASE_GROUP_CREATE)
    public CaseGroupCreateView create(CreateCaseGroupVo createCaseGroupVo) throws ValidationException, DataExistException, DataNotFoundException {
        ValidationUtils.validate(createCaseGroupVo);
        this.caseGroupCreateView.success(caseGroup.save(CaseGroup.valueOf(createCaseGroupVo)));
        return caseGroupCreateView;
    }

}
