package com.alpha.web.services;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.repository.ServicesInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Service
public class ServicesInfoServiceImpl implements IServicesInfoService {

    @Resource
    private ServicesInfoRepository servicesInfoRepository;

    @Override
    public ServicesInfo findById(Long id) {
        return this.servicesInfoRepository.findOne(id);
    }

    @Override
    public ServicesInfo save(ServicesInfo servicesInfo) {
        return this.servicesInfoRepository.save(servicesInfo);
    }

    @Override
    public List<ServicesInfo> find(ServicesInfo servicesInfo) {
        return this.servicesInfoRepository.findAll();
    }
}
