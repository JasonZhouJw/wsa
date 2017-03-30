package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.repository.CaseGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-29.
 */
@Component
public class CaseGroupImpl implements ICaseGroup {

    @Autowired
    private CaseGroupRepository caseGroupRepository;

    @Autowired
    private ITestCase testCase;

    @Override
    public CaseGroup save(CaseGroup caseGroup) throws DataExistException, DataNotFoundException {
        this.checkExist(caseGroup);
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

    @Override
    @Transactional
    public CaseGroup inactive(CaseGroup caseGroup) throws DataExistException, DataNotFoundException {
        CaseGroup existCaseGroup = this.checkExist(caseGroup);
        existCaseGroup.setActive(false);
        testCase.inactive(caseGroup);
        return this.caseGroupRepository.save(existCaseGroup);
    }

    private CaseGroup checkExist(CaseGroup caseGroup) throws DataNotFoundException, DataExistException {
        CaseGroup existCaseGroup = null;
        if (caseGroup.getId() != null && caseGroup.getId() >= 0) {
            existCaseGroup = this.caseGroupRepository.findOne(caseGroup.getId());
            if (existCaseGroup == null) {
                throw new DataNotFoundException();
            } else if (caseGroup.getName() == null || caseGroup.getName().equals(existCaseGroup.getName())) {
                return existCaseGroup;
            }
        }
        existCaseGroup = this.caseGroupRepository.findByName(caseGroup.getName());
        if (existCaseGroup != null) {
            throw new DataExistException();
        }
        return existCaseGroup;
    }
}