package com.alpha.web.services;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.repository.ServicesInfoRepository;
import com.alpha.core.ws.utils.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ServicesInfo update(ServicesInfo servicesInfo) {
        servicesInfo.setActive(false);
        this.servicesInfoRepository.save(servicesInfo);
        ServicesInfo newServicesInfo = (ServicesInfo) BeanCopier.copyBean(servicesInfo, ServicesInfo.class);
        newServicesInfo.setId(null);
        return this.servicesInfoRepository.save(newServicesInfo);
    }

    @Override
    public List<ServicesInfo> find(ServicesInfo servicesInfo) {
        return this.servicesInfoRepository.findAll();
    }

    @Override
    public List<ServicesInfo> findActive() {
        return this.servicesInfoRepository.findActive();
    }
}
