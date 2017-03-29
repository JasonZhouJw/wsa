package com.alpha.testcase.domain;

import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.repository.CaseGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-29.
 */
@Component
public class CaseGroupImpl implements ICaseGroup {

    @Autowired
    private CaseGroupRepository caseGroupRepository;

    @Override
    public CaseGroup save(CaseGroup caseGroup) {
        return this.caseGroupRepository.save(caseGroup);
    }

    @Override
    public List<CaseGroup> findAllActive() {
        CaseGroup caseGroup = new CaseGroup();
        return this.caseGroupRepository.findAll(caseGroup.getExample());
    }

    @Override
    public List<CaseGroup> findAll() {
        CaseGroup caseGroup = new CaseGroup();
        caseGroup.setActive(null);
        return this.caseGroupRepository.findAll(caseGroup.getExample());
    }
}