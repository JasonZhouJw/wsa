package com.alpha.services.domain;

import com.alpha.common.utils.BeanCopier;
import com.alpha.services.entities.ServicesInfo;
import com.alpha.services.repository.ServicesInfosRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jzhou237 on 2016-12-06.
 */
@Component
public class ServicesInfos {

    @Resource
    private ServicesInfosRepository servicesInfosRepository;

    public ServicesInfo findById(Long id) {
        return this.findById(id);
    }

    public ServicesInfo save(ServicesInfo servicesInfo) {
        return (ServicesInfo) this.servicesInfosRepository.save(servicesInfo);
    }

    @Transactional
    public ServicesInfo update(ServicesInfo servicesInfo) {
        servicesInfo.setActive(false);
        this.servicesInfosRepository.save(servicesInfo);
        ServicesInfo newServicesInfo = (ServicesInfo) BeanCopier.copyBean(servicesInfo, ServicesInfo.class);
        newServicesInfo.setId(null);
        return (ServicesInfo) this.servicesInfosRepository.save(newServicesInfo);
    }

    public List<ServicesInfo> find(ServicesInfo servicesInfo) {
        return this.servicesInfosRepository.findAll();
    }

}
