package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.testcase.entities.TestCase;
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

    TestCase update(TestCase testCase) throws DataExistException, DataNotFoundException;

    TestCase findById(Long id);

    TestCase create(TestCase testCase) throws DataExistException, DataNotFoundException;
}
