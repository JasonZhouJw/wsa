package com.alpha.core.ws.parser;

import com.alpha.core.ws.model.InterfaceInfo;
import com.alpha.core.ws.model.WsdlInfo;
import com.alpha.core.ws.utils.ReflectUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 9/8/2016.
 */
public class ClassParser {

    public static List<InterfaceInfo> assembleClass(WsdlInfo wsdlInfo) throws Exception {
        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (String targetMethodName : wsdlInfo.getOperationList()) {
            Method targetMethod = ReflectUtil.getTargetMethod(
                    Class.forName(wsdlInfo.getFacadeClass()), targetMethodName);
            if (targetMethod != null) {
                InterfaceInfo webserviceClassInfo = new InterfaceInfo(wsdlInfo,
                        targetMethod);
                interfaceInfoList.add(webserviceClassInfo);
            }
        }
        return interfaceInfoList;
    }
}
