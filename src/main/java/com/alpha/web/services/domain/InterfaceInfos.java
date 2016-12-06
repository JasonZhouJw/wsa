package com.alpha.web.services.domain;

import com.alpha.core.entity.InterfaceInfo;
import com.alpha.core.repository.InterfaceInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Service
public class InterfaceInfos {

    @Resource
    private InterfaceInfoRepository interfaceInfoRepository;

    public List<InterfaceInfo> findAll() {
        return this.interfaceInfoRepository.findAll();
    }

    public InterfaceInfo save(InterfaceInfo interfaceInfo) {
        return this.interfaceInfoRepository.save(interfaceInfo);
    }
}
