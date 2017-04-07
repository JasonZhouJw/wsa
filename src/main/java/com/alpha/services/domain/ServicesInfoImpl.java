package com.alpha.services.domain;

import com.alpha.services.entities.ServicesInfo;
import com.alpha.services.repository.ServicesInfoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@Component
public class ServicesInfoImpl implements IServicesInfo {

    @Autowired
    private ServicesInfoRepository servicesInfoRepository;

    @Override
    public List<ServicesInfo> findAllActive() {
        ServicesInfo servicesInfo = new ServicesInfo();
        servicesInfo.setWsdl2java(true);
        List<ServicesInfo> servicesInfoList = this.servicesInfoRepository.findAll(servicesInfo.getExample());
        Hibernate.initialize(servicesInfoList);
        return servicesInfoList;
    }

    @Override
    public void findAll(ServicesInfo servicesInfo, Pageable pageable, Consumer<Page<ServicesInfo>> consumer) {
        Page<ServicesInfo> servicesInfoPage = this.servicesInfoRepository.findAll(servicesInfo.getExample(), pageable);
        if (servicesInfoPage != null) {
            Hibernate.initialize(servicesInfoPage);
            consumer.accept(servicesInfoPage);
        }
    }
}
