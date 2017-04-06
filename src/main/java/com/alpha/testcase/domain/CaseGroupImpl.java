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
    public void create(CaseGroup caseGroup, ResultHandler<CaseGroup, CaseGroupVo> resultHandler) {
        try {
            this.checkExist(caseGroup.getId(), caseGroup.getName());
            resultHandler.success(this.caseGroupRepository.save(caseGroup));
        } catch (DomainException e) {
            resultHandler.fail(caseGroup.toVo(), e.getMessage());
        }
    }

    @Override
    public void update(CaseGroupVo caseGroupVo, ResultHandler<CaseGroup, CaseGroupVo> resultHandler) {
        try {
            CaseGroup updatedCaseGroup = this.checkExist(caseGroupVo.getId(), caseGroupVo.getName());
            updatedCaseGroup.copyValue(caseGroupVo);
            resultHandler.success(this.caseGroupRepository.save(updatedCaseGroup));
        } catch (DomainException e) {
            resultHandler.fail(caseGroupVo, e.getMessage());
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
        CaseGroup existCaseGroup = this.checkExist(caseGroup.getId(), caseGroup.getName());
        existCaseGroup.setActive(false);
        testCase.inactive(caseGroup);
        return this.caseGroupRepository.save(existCaseGroup);
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

    private CaseGroup checkExist(Long id, String name) throws DataNotFoundException, DataExistException {
        CaseGroup existCaseGroup = null;
        if (id != null && id >= 0) {
            existCaseGroup = this.caseGroupRepository.findOne(id);
            if (existCaseGroup == null) {
                throw new DataNotFoundException();
            } else if (name == null || name.equals(existCaseGroup.getName())) {
                return existCaseGroup;
            }
        }
        existCaseGroup = this.caseGroupRepository.findByName(name);
        if (existCaseGroup != null) {
            throw new DataExistException();
        }
        return existCaseGroup;
    }
}