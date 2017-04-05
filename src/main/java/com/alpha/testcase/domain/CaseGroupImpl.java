package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.DomainException;
import com.alpha.common.view.ResultHandler;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CaseGroupVo;
import com.alpha.testcase.repository.CaseGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public void save(CaseGroup caseGroup, ResultHandler<CaseGroup, CaseGroupVo> resultHandler) {
        try {
            this.checkExist(caseGroup);
            CaseGroup savedCaseGroup = this.caseGroupRepository.save(caseGroup);
            resultHandler.success(savedCaseGroup);
        } catch (DomainException e) {
            resultHandler.fail(caseGroup.toVo(), e.getMessage());
        }
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
        return this.caseGroupRepository.findAll(caseGroup.getExample(), new Sort(Sort.Direction.DESC, "id"));
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

    @Override
    public CaseGroup findOne(Long id) throws DataNotFoundException {
        CaseGroup caseGroup = null;
        if (id != null || id > 0L) {
            caseGroup = this.caseGroupRepository.findOne(id);
        }
        if (caseGroup == null) {
            throw new DataNotFoundException();
        }
        return caseGroup;
    }
}