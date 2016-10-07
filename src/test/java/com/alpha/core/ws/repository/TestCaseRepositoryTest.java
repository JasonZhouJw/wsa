package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by jzhou237 on 2016-10-07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseRepositoryTest {

    @Autowired
    private TestCaseRepository repository;

    @Autowired
    private InterfaceInfoRepository interfaceInfoRepository;

    @Test
    public void save() {
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setMethodName("method name");
        interfaceInfoRepository.save(interfaceInfo);
        TestCase testCase = new TestCase();
        testCase.setName("name");
        testCase.setVerification("verification");
        testCase.setRequestValue("request value");
        testCase.setInterfaceInfo(interfaceInfo);
        repository.save(testCase);

        List<TestCase> all = repository.findAll();
        System.out.println(all);
    }

}