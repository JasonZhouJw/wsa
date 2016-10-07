package com.alpha.web.services;

import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Service
public class TestCaseServiceImpl implements ITestCaseService {

    @Resource
    private TestCaseRepository testCaseRepository;

    @Override
    public List<TestCase> findAllActive() {
        return this.testCaseRepository.findAllActive();
    }

    @Override
    public TestCase save(TestCase testCase) {
        return this.testCaseRepository.save(testCase);
    }

    @Override
    public void update(TestCase testCase) {
        this.testCaseRepository.save(testCase);
    }

    @Override
    public TestCase findById(Long id) {
        return this.testCaseRepository.findOne(id);
    }
}
