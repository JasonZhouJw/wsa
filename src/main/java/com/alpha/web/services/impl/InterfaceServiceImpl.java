package com.alpha.web.services.impl;

import com.alpha.core.ws.entity.InterfaceInfo;
import com.alpha.core.ws.repository.InterfaceInfoRepository;
import com.alpha.web.services.IInterfaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Service
public class InterfaceServiceImpl implements IInterfaceInfoService {

    @Resource
    private InterfaceInfoRepository interfaceInfoRepository;

    @Override
    public List<InterfaceInfo> findAll() {
        return this.interfaceInfoRepository.findAll();
    }

    @Override
    public InterfaceInfo save(InterfaceInfo interfaceInfo) {
        return this.interfaceInfoRepository.save(interfaceInfo);
    }
}
