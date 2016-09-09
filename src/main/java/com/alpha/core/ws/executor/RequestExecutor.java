package com.alpha.core.ws.executor;

import com.alpha.core.ws.model.InterfaceInfo;
import com.alpha.core.ws.model.WsdlInfo;
import com.alpha.core.ws.parser.ClassParser;
import com.alpha.core.ws.parser.WsdlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/8/2016.
 */
public class RequestExecutor {

    public static List<WsdlInfo> request(String serviceUrl) throws Exception {
        Set<String> wsdlSet = WsdlParser.getWsdl(serviceUrl);
        final List<WsdlInfo> wsdlInfoList = new ArrayList<WsdlInfo>();
        for (String wsdlUrl : wsdlSet) {
            WsdlParser.readWsdl(wsdlUrl, new Consumer<WsdlInfo>() {
                public void accept(WsdlInfo t) {
                    if (t != null) {
                        wsdlInfoList.add(t);
                    }
                }
            });
        }
        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (WsdlInfo wsdlInfo : wsdlInfoList) {
            interfaceInfoList.addAll(ClassParser.assembleClass(wsdlInfo));
        }

//        for (InterfaceInfo webserviceClassInfo : webserviceClassInfoList) {
//            DataXmlTemplateWriter.writeXml("D:/test/temp/" + webserviceClassInfo.getMethodName() + ".xml",
//                    webserviceClassInfo);
//        }
        return wsdlInfoList;
    }
}
