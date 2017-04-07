package com.alpha.testcase.domain;

import com.alpha.common.exceptions.CommonException;
import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.DomainException;
import com.alpha.common.view.ResultHandler;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.repository.TestCaseRepository;
import com.alpha.verifyresult.domain.IVerifyResult;
import com.alpha.wsdl2java.executors.ServiceExecutor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Slf4j
@Component
public class TestCaseImpl implements ITestCase {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private IVerifyResult verifyResult;

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

    public TestCase findById(Long id) throws DataNotFoundException {
        TestCase testCase = null;
        if (id != null && id > 0) {
            testCase = this.testCaseRepository.findOne(id);
        }
        if (testCase == null) {
            throw new DataNotFoundException();
        }
        return testCase;
    }

    @Override
    public void create(TestCase testCase, ResultHandler<TestCase, TestCaseVo> resultHandler) {
        try {
            this.checkUnique(0L, testCase.getName());
            resultHandler.success(this.testCaseRepository.save(testCase));
        } catch (DomainException e) {
            resultHandler.fail(testCase.toVo(), e.getMessage());
        }
    }

    @Override
    public void inactive(CaseGroup caseGroup) {
        this.testCaseRepository.inactiveByGroup(caseGroup.getId());
    }

    @Override
    public void update(TestCaseVo testCaseVo, ResultHandler<TestCase, TestCaseVo> resultHandler) {
        try {
            TestCase updatedTestCase = this.checkUnique(testCaseVo.getId(), testCaseVo.getName());
            updatedTestCase.copyValue(testCaseVo);
            resultHandler.success(this.testCaseRepository.save(updatedTestCase));
        } catch (DomainException e) {
            resultHandler.fail(testCaseVo, e.getMessage());
        }
    }

    private TestCase checkUnique(Long id, String name) throws DataExistException, DataNotFoundException {
        TestCase testCase = null;
        if (id > 0) {
            testCase = this.testCaseRepository.findOne(id);
            if (testCase == null) {
                throw new DataNotFoundException();
            }
        }
        if (testCase == null || !testCase.getName().equals(name) && this.testCaseRepository.findByName(name) != null) {
            throw new DataExistException();
        }
        return testCase;
    }

    @Transactional
    @Override
    public void execute(TestCaseVo testCaseVo, ResultHandler<TestCase, TestCaseVo> resultHandler) {
        this.update(testCaseVo, resultHandler);
        if (resultHandler.isSuccess()) {
            TestCase savedTestCase = resultHandler.getSuccessObj();
            try {
                ServiceExecutor executor = new ServiceExecutor(savedTestCase.getMethodInfo().getServicesInfo().getInterfaceClass(), savedTestCase.getMethodInfo().getMethod());
                executor.execute(savedTestCase);
                verifyResult.saveResult(executor.getResultMap());
            } catch (CommonException e) {
                log.error(e.getMessage(), e);
                resultHandler.fail(testCaseVo, e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                resultHandler.fail(testCaseVo, e.getMessage());
            }
        }

    }
}
