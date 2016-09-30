package com.alpha.core.ws.parser;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.Operation;
import com.alpha.core.ws.entity.Wsdl;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.ReflectUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 9/8/2016.
 */
public class ClassParser implements ILog {

    public static List<InterfaceInfo> assembleClass(Wsdl wsdlInfo) {
        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (Operation operation : wsdlInfo.getOperationList()) {
            Method targetMethod = null;
            try {
                targetMethod = ReflectUtil.getTargetMethod(
                        Class.forName(wsdlInfo.getFacadeClass()), operation.getMethod());
            } catch (ClassNotFoundException e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (targetMethod != null) {
                InterfaceInfo webserviceClassInfo = new InterfaceInfo(wsdlInfo,
                        targetMethod);
                interfaceInfoList.add(webserviceClassInfo);
            }
        }
        return interfaceInfoList;
    }
}
