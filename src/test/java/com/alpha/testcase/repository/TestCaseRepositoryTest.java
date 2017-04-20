package com.alpha.testcase.repository;

import com.alpha.testcase.entities.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jzhou237 on 2017-04-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCaseRepositoryTest {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Test
    public void save() {
        TestCase testCase = new TestCase();
        this.testCaseRepository.save(testCase);
    }
}