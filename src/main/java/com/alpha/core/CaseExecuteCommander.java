package com.alpha.core;

import com.alpha.core.common.exceptions.CommonException;
import com.alpha.core.common.utils.enums.ProtocolType;
import com.alpha.core.common.utils.enums.ResultType;
import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.entity.ServicesInfo;
import com.alpha.core.entity.VerifyResult;
import com.alpha.core.executor.ICaseExecutor;
import com.alpha.core.repository.InterfaceInfoRepository;
import com.alpha.core.repository.ServicesInfoRepository;
import com.alpha.core.repository.VerifyResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Slf4j
@Component
public class CaseExecuteCommander {

    @Resource
    private ServicesInfoRepository servicesInfoRepository;

    @Resource
    private InterfaceInfoRepository interfaceInfoRepository;

    @Resource(name = "executorMap")
    private Map<ProtocolType, ICaseExecutor> executorMap;

    @Resource
    private VerifyResultRepository verifyResultRepository;

    public void execute() {
        List<ServicesInfo> servicesInfoList = this.servicesInfoRepository.findActive();
        servicesInfoList.forEach(servicesInfo -> {
            List<InterfaceInfo> interfaceInfoList = interfaceInfoRepository.findByWsdl(servicesInfo.getId());
            if (interfaceInfoList != null) {
                interfaceInfoList.forEach(interfaceInfo -> {
                    ICaseExecutor executor = getExecutor(servicesInfo);
                    executor.init(interfaceInfo);
                    try {
                        executor.execute();
                    } catch (CommonException e) {
                        log.error(e.getMessage(), e);
                        VerifyResult verifyResult = new VerifyResult();
                        verifyResult.setInterfaceInfo(interfaceInfo);
                        verifyResult.setMessage(e.getMessage());
                        verifyResult.setResult(ResultType.ERROR);
                        verifyResultRepository.save(verifyResult);
                    }
                    executor.last();
                });
            }
        });
    }

    private ICaseExecutor getExecutor(ServicesInfo servicesInfo) {
        return this.executorMap.get(servicesInfo.getProtocolType());
    }

}
