package com.alpha.verifyresult.domain;

import com.alpha.common.enums.ResultType;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.repository.TestCaseRepository;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultSearchVo;
import com.alpha.verifyresult.repository.VerifyResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Created by jzhou237 on 2017-04-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class VerifyResultImplTest {

    @Autowired
    private IVerifyResult verifyResult;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private VerifyResultRepository verifyResultRepository;

    private TestCase testCase;

    @Before
    public void save() {
        List<TestCase> testCaseList = testCaseRepository.findAll();
        if (testCaseList.size() == 0) {
            return;
        }
        testCase = testCaseList.get(0);
        VerifyResult verifyResult = new VerifyResult();
        verifyResult.setTestCase(testCase);
        verifyResult.setMessage("success");
        verifyResult.setResult(ResultType.SUCCESS);
        this.verifyResultRepository.save(verifyResult);
    }

    @Test
    public void search() throws Exception {
        VerifyResultSearchVo verifyResultSearchVo = new VerifyResultSearchVo();
        TestCaseVo testCaseVo = new TestCaseVo();
        testCaseVo.setId(this.testCase.getId());
        verifyResultSearchVo.setTestCase(testCaseVo);
        this.verifyResult.search(verifyResultSearchVo, new PageRequest(0, 10), new Consumer<Page<VerifyResult>>() {
            @Override
            public void accept(Page<VerifyResult> verifyResults) {
                assertEquals(1, verifyResults.getTotalElements());
            }
        });
    }

}