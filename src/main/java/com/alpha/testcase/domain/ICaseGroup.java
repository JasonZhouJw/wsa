package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.view.ResultHandler;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CaseGroupVo;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-29.
 */
public interface ICaseGroup {

    void save(CaseGroup caseGroup, ResultHandler<CaseGroup, CaseGroupVo> resultHandler);

    List<CaseGroup> findAllActive();

    List<CaseGroup> findAll();

    CaseGroup inactive(CaseGroup caseGroup) throws DataExistException, DataNotFoundException;

    CaseGroup findOne(Long id) throws DataNotFoundException;
}
