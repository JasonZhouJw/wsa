package com.alpha.core.ws;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.entity.VerifyResult;
import com.alpha.core.ws.exception.CommonException;
import com.alpha.core.ws.executor.ICaseExecutor;
import com.alpha.core.ws.repository.InterfaceInfoRepository;
import com.alpha.core.ws.repository.ServicesInfoRepository;
import com.alpha.core.ws.repository.VerifyResultRepository;
import com.alpha.core.ws.utils.ILog;
import com.alpha.core.ws.utils.enums.ProtocolType;
import com.alpha.core.ws.utils.enums.ResultType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Component
public class CaseExecuteCommander implements ILog {

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
                        LOGGER.error(e.getMessage(), e);
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
