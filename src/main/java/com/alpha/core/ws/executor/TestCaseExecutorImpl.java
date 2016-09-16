package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.TestCase;
import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.repository.TestCaseRepository;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.ReflectUtil;
import com.alpha.core.ws.utils.enums.Errors;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 9/9/2016.
 */
@Component
@Scope("prototype")
public class TestCaseExecutorImpl implements ITestCaseExecutor, ILog {

    private Map<String, List<VerifyResult>> resultMap = new HashMap<String, List<VerifyResult>>();

    private InterfaceInfo interfaceInfo;

    private List<TestCase> testCaseList = new ArrayList<TestCase>();

    @Resource
    private VerifyResultRepository verifyResultRepository;

    @Resource
    private TestCaseRepository testCaseRepository;

    @Override
    public void init(InterfaceInfo interfaceInfo) {
        //TODO: 9/9/2016  get the wsdl data, propare the request data
        this.interfaceInfo = interfaceInfo;
        testCaseList = testCaseRepository.findByInterfaceId(this.interfaceInfo.getId());
        this.testCaseList.forEach(testCase -> {
            List<VerifyResult> verifyResultList = new ArrayList<VerifyResult>(testCase.getVerifyList().size());
            testCase.getVerifyList().forEach(verifyInfo -> {
                verifyResultList.add(new VerifyResult(verifyInfo));
            });
            this.resultMap.put(testCase.getName(), verifyResultList);
        });
    }

    @Override
    public void request() throws CommonException {
        //TODO: 9/9/2016  get the interface information and request the webservice, store the request data
        //TODO: 9/9/2016  it will support other request type, so request handler should be use the factory model
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
            this.testCaseList.forEach(testCase -> {
                Parameter[] parameters = targetMethod.getParameters();
                Object[] dataParameters = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Object parameterObj = null;
                    try {
                        parameterObj = parameter.getType().newInstance();
                    } catch (InstantiationException e) {
                        LOGGER.error(e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //TODO: 组装数据
                    dataParameters[i] = parameterObj;
                }
                if (targetMethod.getReturnType() != null) {
                    Object returnObj = null;
                    try {
                        returnObj = targetMethod.invoke(service, dataParameters);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    //TODO:验证返回
                    LOGGER.info(interfaceInfo.getWsdl().getFacadeClass() + Constants.DOT
                            + interfaceInfo.getMethodName() + ":" + returnObj);
                } else {
                    try {
                        targetMethod.invoke(service, dataParameters);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    LOGGER.info(interfaceInfo.getWsdl().getFacadeClass() + Constants.DOT
                            + interfaceInfo.getMethodName() + " Success");
                }
            });
        }
    }

    @Override
    public void response() {
        // TODO: 9/9/2016  store the response and reconstruct the response for validation
    }

    @Override
    public void verify() {
        // TODO: 9/9/2016 verify the response and store the result

    }

    @Override
    public void last() {
        // TODO: 9/9/2016 other work to do
    }
}
