package com.alpha.core.ws.executor;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.entity.Wsdl;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.parser.ClassParser;
import com.alpha.core.ws.parser.WsdlParser;
import com.alpha.core.ws.repository.InterfaceInfoRepository;
import com.alpha.core.ws.repository.WsdlRepository;
import com.alpha.core.ws.utils.ILog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Component
public class WsdlAssembleExecutor implements ILog {

    @Resource
    private WsdlRepository wsdlRepository;

    @Resource
    private InterfaceInfoRepository interfaceInfoRepository;

    public void execute(ServicesInfo servicesInfo) {
        Set<String> wsdlSet = null;
        final List<Wsdl> wsdlList = new ArrayList<Wsdl>();
        try {
            wsdlSet = WsdlParser.getWsdl(servicesInfo.getService());
            for (String wsdlUrl : wsdlSet) {
                WsdlParser.readWsdl(wsdlUrl, new Consumer<Wsdl>() {
                    public void accept(Wsdl t) {
                        if (t != null) {
                            t.setServicesInfo(servicesInfo);
                            wsdlList.add(t);
                        }
                    }
                });
            }
        } catch (CommonException e) {
            LOGGER.error(e.getMessage(), e);
        }

        wsdlRepository.save(wsdlList);

        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (Wsdl wsdlInfo : wsdlList) {
            try {
                interfaceInfoList.addAll(ClassParser.assembleClass(wsdlInfo));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        interfaceInfoRepository.save(interfaceInfoList);

    }
}
