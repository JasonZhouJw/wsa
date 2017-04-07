package com.alpha.testcase.domain;

import com.alpha.common.model.Result;
import com.alpha.common.view.ResultHandler;
import com.alpha.services.entities.MethodInfo;
import com.alpha.services.entities.ServicesInfo;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import com.alpha.testcase.repository.TestCaseRepository;
import com.alpha.verifyresult.domain.IVerifyResult;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.wsdl2java.executors.ServiceExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TestCaseImpl.class)
public class TestCaseImplTest {


    @InjectMocks
    private ITestCase testCase = new TestCaseImpl();

    @Mock
    private TestCaseRepository repository;

    @Mock
    private IVerifyResult verifyResult;


    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findAllActive() throws Exception {
        when(this.repository.findAll((Example<TestCase>) anyObject())).thenAnswer(new Answer<List<TestCase>>() {
            @Override
            public List<TestCase> answer(InvocationOnMock invocation) throws Throwable {
                List<TestCase> testCaseList = new ArrayList<>();
                TestCase testCase = new TestCase();
                testCase.setId(1L);
                testCaseList.add(testCase);
                return testCaseList;
            }
        });
        List<TestCase> actual = this.testCase.findAllActive();
        assertEquals(1, actual.size());
        assertEquals(Long.valueOf(1L), actual.get(0).getId());
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

    @Test
    public void execute() throws Exception {
        ServiceExecutor executor = mock(ServiceExecutor.class);

        whenNew(ServiceExecutor.class).withArguments(anyString(), anyString()).thenReturn(executor);

        doNothing().when(executor).execute(anyObject());

        Map<String, VerifyResult> resultMap = new HashMap<>();
        when(executor.getResultMap()).thenReturn(resultMap);

        when(repository.findByName("ut")).thenReturn(null);

        TestCase existTestCase = new TestCase();
        existTestCase.setName("exist");
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethod("method");
        ServicesInfo servicesInfo = new ServicesInfo();
        servicesInfo.setInterfaceClass("class");
        methodInfo.setServicesInfo(servicesInfo);
        existTestCase.setMethodInfo(methodInfo);
        when(repository.findOne(1L)).thenReturn(existTestCase);

        existTestCase = new TestCase();
        existTestCase.setId(1L);
        existTestCase.setName("ut");
        methodInfo = new MethodInfo();
        methodInfo.setMethod("method");
        servicesInfo = new ServicesInfo();
        servicesInfo.setInterfaceClass("class");
        methodInfo.setServicesInfo(servicesInfo);
        existTestCase.setMethodInfo(methodInfo);
        when(repository.save((TestCase) anyObject())).thenReturn(existTestCase);

        ResultHandler<TestCase, TestCaseVo> resultHandler = new ResultHandler<>(new Consumer<Result<TestCase>>() {
            @Override
            public void accept(Result<TestCase> testCaseResult) {
                assertNotNull(testCaseResult.getResult());
                assertEquals(Long.valueOf(1L), testCaseResult.getResult().getId());
                assertEquals("ut", testCaseResult.getResult().getName());
            }
        }, new Consumer<Result<TestCaseVo>>() {
            @Override
            public void accept(Result<TestCaseVo> testCaseVoResult) {
                fail();
            }
        });

        doNothing().when(this.verifyResult).saveResult(anyObject());

        TestCaseVo testCaseVo = new TestCaseVo();
        testCaseVo.setId(1L);
        testCaseVo.setName("ut");
        this.testCase.execute(testCaseVo, resultHandler);

        verifyNew(ServiceExecutor.class).withArguments(anyString(), anyString());

        assertNotNull(resultHandler.getSuccessObj());
    }


}