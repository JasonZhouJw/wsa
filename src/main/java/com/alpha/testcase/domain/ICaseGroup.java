package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.testcase.entities.CaseGroup;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-29.
 */
public interface ICaseGroup {

    CaseGroup save(CaseGroup caseGroup) throws DataExistException, DataNotFoundException;

    List<CaseGroup> findAllActive();

    List<CaseGroup> findAll();

    CaseGroup inactive(CaseGroup caseGroup) throws DataExistException, DataNotFoundException;

    CaseGroup findOne(Long id) throws DataNotFoundException;
}
