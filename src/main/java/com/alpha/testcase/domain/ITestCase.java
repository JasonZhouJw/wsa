package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.view.ResultHandler;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.model.UpdateTestCaseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-16.
 */
public interface ITestCase {

    void findAll(TestCase testCase, Pageable pageable, Consumer<Page<TestCase>> consumer);

    List<TestCase> findAllActive();

    TestCase findById(Long id) throws DataNotFoundException;

    void create(TestCase testCase, ResultHandler<TestCase, TestCaseVo> resultHandler) throws DataExistException, DataNotFoundException;

    void inactive(CaseGroup caseGroup);

    void update(UpdateTestCaseVo testCaseVo, ResultHandler<TestCase, TestCaseVo> resultHandler);

    void execute(TestCaseVo testCaseVo, ResultHandler<TestCase, TestCaseVo> resultHandler);
}
