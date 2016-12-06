package com.alpha.core.parser;

import com.alpha.core.common.utils.ReflectUtil;
import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.entity.Operation;
import com.alpha.core.entity.Wsdl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Slf4j
public class ClassParser {

    public static List<InterfaceInfo> assembleClass(Wsdl wsdlInfo) {
        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (Operation operation : wsdlInfo.getOperationList()) {
            Method targetMethod = null;
            try {
                targetMethod = ReflectUtil.getTargetMethod(
                        Class.forName(wsdlInfo.getFacadeClass()), operation.getMethod());
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
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
