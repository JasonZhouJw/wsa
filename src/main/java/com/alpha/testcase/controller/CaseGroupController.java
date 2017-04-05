package com.alpha.testcase.controller;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.ValidationException;
import com.alpha.common.utils.ValidationUtils;
import com.alpha.testcase.domain.ICaseGroup;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CreateCaseGroupVo;
import com.alpha.testcase.model.UpdateCaseGroupVo;
import com.alpha.testcase.view.CaseGroupCreateView;
import com.alpha.testcase.view.CaseGroupIndex;
import com.alpha.testcase.view.CaseGroupUpdateView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.alpha.common.controller.Urls.*;
import static com.alpha.common.utils.Constants.DANGER;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Slf4j
@Controller
public class CaseGroupController {

    @Autowired
    private ICaseGroup caseGroup;

    @Autowired
    private CaseGroupIndex caseGroupIndex;

    @Autowired
    private CaseGroupCreateView caseGroupCreateView;

    @Autowired
    private CaseGroupUpdateView caseGroupUpdateView;

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
//        this.caseGroupCreateView.success(caseGroup.save(CaseGroup.valueOf(createCaseGroupVo)));
        return caseGroupCreateView;
    }

    @GetMapping(CASE_GROUP_TO_UPDATE + "/{id}")
    public CaseGroupUpdateView toUpdate(@PathVariable("id") Long id) throws DataNotFoundException {
        this.caseGroupUpdateView.setCaseGroup(this.caseGroup.findOne(id));
        return caseGroupUpdateView;
    }

    @PostMapping(CASE_GROUP_UPDATE)
    public CaseGroupUpdateView update(UpdateCaseGroupVo updateCaseGroupVo) {
        CaseGroup caseGroup = CaseGroup.valueOf(updateCaseGroupVo);
        try {
            ValidationUtils.validate(updateCaseGroupVo);
            this.caseGroup.save(caseGroup, this.caseGroupUpdateView.getResultHandler());
        } catch (ValidationException e) {
            log.warn(e.getAllMessage());
            this.caseGroupUpdateView.getResultHandler().fail(updateCaseGroupVo, e.getMessages(), DANGER);
        }
        return caseGroupUpdateView;
    }
}
