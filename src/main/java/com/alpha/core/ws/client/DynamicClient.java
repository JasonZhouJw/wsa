package com.alpha.core.ws.client;

import com.alpha.core.ws.model.WsdlInfo;
import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.ReflectUtil;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class DynamicClient implements ILog {

    public static void request(WsdlInfo wsdlInfo) {
        try {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(Class.forName(wsdlInfo.getFacadeClass()));
            factory.setAddress(wsdlInfo.getAddress());
            Object service = factory.create();
            for (String targetMethodName : wsdlInfo.getOperationList()) {
                Method targetMethod = ReflectUtil.getTargetMethod(
                        Class.forName(wsdlInfo.getFacadeClass()), targetMethodName);
                if (targetMethod != null) {
                    Parameter[] parameters = targetMethod.getParameters();
                    Object[] dataParameters = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        Object parameterObj = parameter.getType().newInstance();
                        //TODO: 组装数据
                        dataParameters[i] = parameterObj;
                    }
                    if (targetMethod.getReturnType() != null) {
                        Object returnObj = targetMethod.invoke(service, dataParameters);
                        //TODO:验证返回
                        LOGGER.info(wsdlInfo.getFacadeClass() + Constants.DOT
                                + targetMethodName + ":" + returnObj);
                    } else {
                        targetMethod.invoke(service, dataParameters);
                        LOGGER.info(wsdlInfo.getFacadeClass() + Constants.DOT
                                + targetMethodName + " Success");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ReflectiveOperationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
