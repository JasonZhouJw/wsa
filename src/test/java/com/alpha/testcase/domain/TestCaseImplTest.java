package com.alpha.testcase.domain;

import com.alpha.testcase.entities.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCaseImplTest {


    @Autowired
    private ITestCase testCase;

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findAllActive() throws Exception {
        List<TestCase> actual = this.testCase.findAllActive();
        assertEquals(1, actual.size());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

}