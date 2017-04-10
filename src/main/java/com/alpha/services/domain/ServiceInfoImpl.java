package com.alpha.services.domain;

import com.alpha.common.view.ResultHandler;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoVo;
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
    public List<ServiceInfo> findAllActive() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setWsdl2java(true);
        List<ServiceInfo> serviceInfoList = this.servicesInfoRepository.findAll(serviceInfo.getExample());
        Hibernate.initialize(serviceInfoList);
        return serviceInfoList;
    }

    @Override
    public void findAll(ServiceInfo serviceInfo, Pageable pageable, Consumer<Page<ServiceInfo>> consumer) {
        Page<ServiceInfo> servicesInfoPage = this.servicesInfoRepository.findAll(serviceInfo.getExample(), pageable);
        if (servicesInfoPage != null) {
            Hibernate.initialize(servicesInfoPage);
            consumer.accept(servicesInfoPage);
        }
    }

    @Override
    public void create(ServiceInfoVo serviceInfoVo, ResultHandler<ServiceInfo, ServiceInfoVo> resultHandler) {
        ServiceInfo savedServiceInfo = this.servicesInfoRepository.save(ServiceInfo.valueOf(serviceInfoVo));
        if (savedServiceInfo != null) {
            resultHandler.success(savedServiceInfo);
        }
    }
}
