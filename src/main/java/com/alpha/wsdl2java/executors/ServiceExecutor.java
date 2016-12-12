package com.alpha.wsdl2java.executors;

import com.alpha.common.enums.ResultType;
import com.alpha.common.exceptions.CommonException;
import com.alpha.common.exceptions.VerificationException;
import com.alpha.loader.ServicesLoader;
import com.alpha.loader.entities.ServiceMethod;
import com.alpha.loader.entities.ServicesClass;
import com.alpha.testcase.entities.TestCase;
import com.alpha.validation.AbsVerification;
import com.alpha.validation.VerificationFactory;
import com.alpha.verifyresult.entities.VerifyResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-12-09.
 */
@Slf4j
public class ServiceExecutor {

    private Object serviceObj;

    private Method serviceMethod;

    @Getter
    private Map<String, VerifyResult> resultMap = new HashMap<>();

    private Yaml yaml = new Yaml();

    public ServiceExecutor(String services, String servicesMethod) {
        ServicesClass servicesClass = ServicesLoader.getInstance().get(services);
        if (servicesClass == null) {
            throw new NullPointerException("can not find the Services:" + services);
        }
        ServiceMethod serviceMethod = servicesClass.getServicesMethods().get(servicesMethod);
        if (serviceMethod == null) {
            throw new NullPointerException("can not find the method [" + servicesMethod + "] in " + services);
        }
        this.serviceMethod = serviceMethod.getMethod();
        try {
            this.serviceObj = servicesClass.getServicesObject();
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void execute(TestCase testCase) throws CommonException {
        // TODO: 2016-12-12 maybe not fit for the all type of parameters. same as verification.
        Object[] dataParameters = yaml.loadAs(testCase.getRequestValue(), ArrayList.class).toArray();
        Object responseObj = null;
        try {
            responseObj = serviceMethod.invoke(serviceObj, dataParameters);
            this.verify(testCase, responseObj);
        } catch (IllegalAccessException e) {
            throw new CommonException(e);
        } catch (InvocationTargetException e) {
            throw new CommonException(e);
        }
    }

    private void verify(TestCase testCase, Object response) {
        List<Map<String, String>> verificationMapList = yaml.loadAs(testCase.getVerification(), ArrayList.class);
        verificationMapList.forEach(verificationMap -> {
            AbsVerification verification = VerificationFactory.getVerification(verificationMap);
            VerifyResult result = new VerifyResult();
            result.setMethodInfo(testCase.getMethodInfo());
            result.setTestCase(testCase);
            try {
                verification.verify(response);
            } catch (VerificationException e) {
                result.setResult(ResultType.FAIL);
                result.setMessage(e.getMessage());
            } catch (CommonException e) {
                log.error(e.getMessage(), e);
                result.setResult(ResultType.ERROR);
                result.setMessage(e.getMessage());
            }
            this.resultMap.put(testCase.getName(), result);
        });
    }

}
