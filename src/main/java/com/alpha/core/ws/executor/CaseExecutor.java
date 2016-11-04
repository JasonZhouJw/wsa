package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.exception.VerificationException;
import com.alpha.core.ws.repository.TestCaseRepository;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.ReflectUtil;
import com.alpha.core.ws.utils.enums.Errors;
import com.alpha.core.ws.utils.enums.ResultType;
import com.alpha.core.ws.validation.AbsVerification;
import com.alpha.core.ws.validation.VerificationFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
@Component("caseExecutor")
@Scope("prototype")
public class CaseExecutor implements ILog, ICaseExecutor {

    private Map<String, List<VerifyResult>> resultMap = new HashMap<String, List<VerifyResult>>();

    private InterfaceInfo interfaceInfo;

    private List<TestCase> testCaseList = new ArrayList<TestCase>();

    private Yaml yaml = new Yaml();

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
    public void init(TestCase testCase) {
        this.testCaseList = new ArrayList<TestCase>();
        this.testCaseList.add(testCase);
        this.resultMap.put(testCase.getName(), new ArrayList<VerifyResult>());
    }

    @Override
    public void execute() throws CommonException {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        Class clazz = null;
        try {
            ClassLoader clazzLoad = new URLClassLoader(new URL[]{new URL(interfaceInfo.getWsdl().getServicesInfo().getPath())});
            clazz = clazzLoad.loadClass(interfaceInfo.getWsdl().getFacadeClass());
        } catch (ClassNotFoundException e) {
            throw new CommonException(Errors.CLASS_NOT_FOUND);
        } catch (MalformedURLException e) {
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
                Object[] dataParameters = yaml.loadAs(testCase.getRequestValue(), ArrayList.class).toArray();
                Object responseObj = null;
                try {
                    responseObj = targetMethod.invoke(service, dataParameters);
                    this.verify(testCase, responseObj);
                } catch (IllegalAccessException e) {
                    throw new CommonException(e);
                } catch (InvocationTargetException e) {
                    throw new CommonException(e);
                }
                LOGGER.info(interfaceInfo.getWsdl().getFacadeClass() + Constants.DOT
                        + interfaceInfo.getMethodName() + ":" + responseObj);
            }
        }
    }

    @Override
    public void verify(TestCase testCase, Object response) {
        List<Map<String, String>> verificationMapList = yaml.loadAs(testCase.getVerification(), ArrayList.class);
        verificationMapList.forEach(verificationMap -> {
            AbsVerification verification = VerificationFactory.getVerification(verificationMap);
            VerifyResult result = new VerifyResult(testCase);
            result.setInterfaceInfo(this.interfaceInfo);
            try {
                verification.verify(response);
            } catch (VerificationException e) {
                result.setResult(ResultType.FAIL);
                result.setMessage(e.getMessage());
            } catch (CommonException e) {
                LOGGER.error(e.getMessage(), e);
                result.setResult(ResultType.ERROR);
                result.setMessage(e.getMessage());
            }
            this.resultMap.get(testCase.getName()).add(result);
        });
    }

    @Override
    public void last() {
        this.resultMap.forEach((name, verifyResultList) -> {
            verifyResultRepository.save(verifyResultList);
        });
    }
}
