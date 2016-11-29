package com.alpha.web.services.impl;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.web.services.IVerifyResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by jzhou237 on 2016-11-28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyResultServiceImplTest {

    @Autowired
    private IVerifyResultService verifyResultService;

    @Test
    public void search() throws Exception {
//        VerifyResult verifyResult=new VerifyResult();
//        InterfaceInfo interfaceInfo=new InterfaceInfo();
//        interfaceInfo.setId(1L);
//        TestCase testCase=new TestCase();
//        testCase.setId(1L);
//        testCase.setInterfaceInfo(interfaceInfo);
//        verifyResult.setTestCase(testCase);
//        verifyResult.setInterfaceInfo(interfaceInfo);
//        verifyResultService.search(verifyResult);

    }

}