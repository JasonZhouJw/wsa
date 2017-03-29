package com.alpha.testcase.domain;

import com.alpha.testcase.entities.CaseGroup;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-29.
 */
public interface ICaseGroup {

    CaseGroup save(CaseGroup caseGroup);

    List<CaseGroup> findAllActive();

    List<CaseGroup> findAll();
}
