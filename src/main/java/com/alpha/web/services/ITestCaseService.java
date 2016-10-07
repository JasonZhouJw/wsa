package com.alpha.web.services;

import com.alpha.core.ws.entity.TestCase;

import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
public interface ITestCaseService {

    List<TestCase> findAllActive();

    TestCase save(TestCase testCase);

    void update(TestCase testCase);

    TestCase findById(Long id);
}
