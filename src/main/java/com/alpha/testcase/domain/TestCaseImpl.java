package com.alpha.testcase.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.repository.TestCaseRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Component
public class TestCaseImpl implements ITestCase {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public void findAll(TestCase testCase, Pageable pageable, Consumer<Page<TestCase>> consumer) {
        Page<TestCase> testCasePage = this.testCaseRepository.findAll(testCase.getExample(), pageable);
        if (testCasePage != null) {
            Hibernate.initialize(testCasePage);
            consumer.accept(testCasePage);
        }
    }

    public List<TestCase> findAllActive() {
        return this.testCaseRepository.findAll(new TestCase().getExample());
    }

    public TestCase update(TestCase testCase) throws DataExistException, DataNotFoundException {
        this.checkUnique(testCase.getId(), testCase.getName());
        return this.testCaseRepository.save(testCase);
    }

    public TestCase findById(Long id) {
        return this.testCaseRepository.findOne(id);
    }

    @Override
    public TestCase create(TestCase testCase) throws DataExistException, DataNotFoundException {
        this.checkUnique(0L, testCase.getName());
        return this.testCaseRepository.save(testCase);
    }

    @Override
    public void inactive(CaseGroup caseGroup) {
        this.testCaseRepository.inactiveByGroup(caseGroup.getId());
    }

    private void checkUnique(Long id, String name) throws DataExistException, DataNotFoundException {
        TestCase testCase = null;
        if (id > 0) {
            testCase = this.testCaseRepository.findOne(id);
            if (testCase == null) {
                throw new DataNotFoundException();
            }
        }
        if (testCase == null || !testCase.getName().equals(name)) {
            testCase = this.testCaseRepository.findByName(name);
            if (testCase != null) {
                throw new DataExistException();
            }
        }
    }

}
