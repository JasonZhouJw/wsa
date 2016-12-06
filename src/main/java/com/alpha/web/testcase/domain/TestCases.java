package com.alpha.web.testcase.domain;

import com.alpha.core.entity.TestCase;
import com.alpha.core.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Service
public class TestCases {

    @Resource
    private TestCaseRepository testCaseRepository;

    public List<TestCase> findAllActive() {
        return this.testCaseRepository.findAllActive();
    }

    public TestCase save(TestCase testCase) {
        return this.testCaseRepository.save(testCase);
    }

    public void update(TestCase testCase) {
        this.testCaseRepository.save(testCase);
    }

    public TestCase findById(Long id) {
        return this.testCaseRepository.findOne(id);
    }
}
