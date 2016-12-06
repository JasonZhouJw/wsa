package com.alpha.core.executor;

import com.alpha.core.cache.Caches;
import com.alpha.core.common.exceptions.CommonException;
import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.entity.ServicesInfo;
import com.alpha.core.entity.Wsdl;
import com.alpha.core.parser.ClassParser;
import com.alpha.core.parser.WsdlParser;
import com.alpha.core.repository.InterfaceInfoRepository;
import com.alpha.core.repository.WsdlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Slf4j
@Component
public class WsdlAssembleExecutor {

    @Resource
    private WsdlRepository wsdlRepository;

    @Resource
    private InterfaceInfoRepository interfaceInfoRepository;

    /**
     * assemble the WSDL information and refresh the cache
     *
     * @param servicesInfo
     */
    public void execute(ServicesInfo servicesInfo) {
        Map<String, Map<String, InterfaceInfo>> dataMap = this.assemble(servicesInfo);
        Caches.SERVICES.refresh(new Supplier<Map<String, Object>>() {
            @Override
            public Map<String, Object> get() {
                Map<String, Object> interfaceMap = new HashMap<String, Object>();
                interfaceMap.putAll(dataMap);
                return interfaceMap;
            }
        });
    }

    /**
     * refresh the wsdl information. save the interface information and mapping wsdl.
     *
     * @param servicesInfo
     * @return Map <K1, Map<K2, V>> K1: Facade Class Name , K2: Method Full Name , V: InterfaceInfo
     */
    @Transactional
    private Map<String, Map<String, InterfaceInfo>> assemble(ServicesInfo servicesInfo) {
        this.wsdlRepository.inactive(servicesInfo.getId());
        Map<String, Map<String, InterfaceInfo>> dataMap = new HashMap<String, Map<String, InterfaceInfo>>();
        final List<Wsdl> wsdlList = new ArrayList<Wsdl>();
        try {
            if (servicesInfo.isWsdl()) {
                WsdlParser.readWsdl(servicesInfo.getService(), new Consumer<Wsdl>() {
                    public void accept(Wsdl t) {
                        if (t != null) {
                            t.setServicesInfo(servicesInfo);
                            wsdlList.add(t);
                        }
                    }
                });
            } else {
                for (String wsdlUrl : WsdlParser.getWsdl(servicesInfo.getService())) {
                    WsdlParser.readWsdl(wsdlUrl, new Consumer<Wsdl>() {
                        public void accept(Wsdl t) {
                            if (t != null) {
                                t.setServicesInfo(servicesInfo);
                                wsdlList.add(t);
                            }
                        }
                    });
                }
            }
        } catch (CommonException e) {
            log.error(e.getMessage(), e);
        }

        List<Wsdl> savedWsdlList = wsdlRepository.save(wsdlList);

        this.interfaceInfoRepository.inactiveByService(servicesInfo.getId());
        List<InterfaceInfo> interfaceInfoList = new ArrayList<InterfaceInfo>();
        for (Wsdl wsdlInfo : savedWsdlList) {
            try {
                List<InterfaceInfo> wsdlInterfaceList = ClassParser.assembleClass(wsdlInfo);
                dataMap.put(wsdlInfo.getFacadeClass(), new HashMap<String, InterfaceInfo>());
                wsdlInterfaceList.forEach(interfaceInfo -> {
                    dataMap.get(wsdlInfo.getFacadeClass()).put(interfaceInfo.getFullName(), interfaceInfo);
                });
                interfaceInfoList.addAll(wsdlInterfaceList);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        List<InterfaceInfo> savedInterfaceList = interfaceInfoRepository.save(interfaceInfoList);
        return dataMap;
    }

}
