package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.exception.ValidationException;
import com.alpha.core.ws.repository.TestCaseRepository;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.utils.YamlUtils;
import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.ReflectUtil;
import com.alpha.core.ws.utils.enums.Errors;
import com.alpha.core.ws.validation.AbsVerification;
import com.alpha.core.ws.validation.VerificationFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test Case executor for one interface
 * Base on YAML
 * <p>
 * include 3 steps, init, execute and last. The verify will be executed for each test case
 */
@Component
@Scope("prototype")
public class CaseExecutor implements ICaseExecutor, ILog {

    private Map<String, List<VerifyResult>> resultMap = new HashMap<String, List<VerifyResult>>();

    private InterfaceInfo interfaceInfo;

    private List<TestCase> testCaseList = new ArrayList<TestCase>();

    @Resource
    private TestCaseRepository testCaseRepository;

    @Resource
    private VerifyResultRepository verifyResultRepository;

    @Override
    public void init(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
        testCaseList = testCaseRepository.findByInterfaceId(this.interfaceInfo.getId());
        this.testCaseList.forEach(testCase -> {
            this.resultMap.put(testCase.getName(), new ArrayList<VerifyResult>());
        });
    }

    @Override
    public void execute() throws CommonException {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        Class clazz = null;
        try {
            clazz = Class.forName(interfaceInfo.getWsdl().getFacadeClass());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CommonException(Errors.CLASS_NOT_FOUND);
        }
        if (clazz == null) {
            throw new CommonException(Errors.CLASS_NOT_FOUND);
        }
        factory.setServiceClass(clazz);
        factory.setAddress(interfaceInfo.getWsdl().getAddress());
        Object service = factory.create();
        Method targetMethod = ReflectUtil.getTargetMethod(clazz, interfaceInfo.getMethodName());
        if (targetMethod != null) {
            for (TestCase testCase : this.testCaseList) {
                Object[] dataParameters = ((ArrayList) YamlUtils.load(testCase.getRequestValue(), ArrayList.class)).toArray();
                Object responseObj = null;
                try {
                    responseObj = targetMethod.invoke(service, dataParameters);
                    this.verify(testCase, responseObj);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage(), e);
                    // TODO: 2016-09-21 how to handle the system exception
                    throw new CommonException(e);
                } catch (InvocationTargetException e) {
                    LOGGER.error(e.getMessage(), e);
                    throw new CommonException(e);
                }
                LOGGER.info(interfaceInfo.getWsdl().getFacadeClass() + Constants.DOT
                        + interfaceInfo.getMethodName() + ":" + responseObj);
            }
        }
    }

    @Override
    public void verify(TestCase testCase, Object response) {
        List<Map<String, String>> verificationMapList = (List<Map<String, String>>) YamlUtils.load(testCase.getVerification(), ArrayList.class);
        verificationMapList.forEach(verificationMap -> {
            AbsVerification verification = VerificationFactory.getVerification(verificationMap);
           VerifyResult result= new VerifyResult(testCase);
            try {
                verification.verify(response);
            } catch (ValidationException e) {
                result.setResult(false);
                result.setMessage(e.getMessage());
            } catch (CommonException e) {
                LOGGER.error(e.getMessage(), e);
                result.setResult(false);
                result.setMessage(e.getMessage());
            }
            this.resultMap.get(testCase.getName()).add(result);
        });
    }

    @Override
    public void last() {
        this.resultMap.forEach((name,verifyResultList)->{
            verifyResultRepository.save(verifyResultList);
        });
    }
}
