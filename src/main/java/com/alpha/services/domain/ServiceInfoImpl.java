package com.alpha.services.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.exceptions.DomainException;
import com.alpha.common.view.ResultHandler;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoUpdateVo;
import com.alpha.services.model.ServiceInfoVo;
import com.alpha.services.repository.ServiceInfoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-20.
 */
@Component
public class ServiceInfoImpl implements IServiceInfo {

    @Autowired
    private ServiceInfoRepository serviceInfoRepository;

    @Override
    public List<ServiceInfo> findAllActive() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setWsdl2java(true);
        List<ServiceInfo> serviceInfoList = this.serviceInfoRepository.findAll(serviceInfo.getExample());
        Hibernate.initialize(serviceInfoList);
        return serviceInfoList;
    }

    @Override
    public void findAll(ServiceInfo serviceInfo, Pageable pageable, Consumer<Page<ServiceInfo>> consumer) {
        Page<ServiceInfo> servicesInfoPage = this.serviceInfoRepository.findAll(serviceInfo.getExample(), pageable);
        if (servicesInfoPage != null) {
            Hibernate.initialize(servicesInfoPage);
            consumer.accept(servicesInfoPage);
        }
    }

    @Override
    public void create(ServiceInfoVo serviceInfoVo, ResultHandler<ServiceInfo, ServiceInfoVo> resultHandler) {
        ServiceInfo savedServiceInfo = this.serviceInfoRepository.save(ServiceInfo.valueOf(serviceInfoVo));
        if (savedServiceInfo != null) {
            resultHandler.success(savedServiceInfo);
        }
    }

    @Transactional
    @Override
    public void assemble(Long id, String serviceName) {
        ServiceInfo serviceInfo = this.serviceInfoRepository.findOneWithLock(id);
        if (serviceInfo != null) {
            serviceInfo.setWsdl2java(true);
            serviceInfo.setInterfaceClass(serviceName);
            serviceInfo.update();
            this.serviceInfoRepository.save(serviceInfo);
        }
    }

    @Override
    public ServiceInfo findOne(Long id) throws DataNotFoundException {
        ServiceInfo serviceInfo = this.serviceInfoRepository.findOne(id);
        if (serviceInfo == null) {
            throw new DataNotFoundException();
        }
        return serviceInfo;
    }

    @Override
    public void update(ServiceInfoUpdateVo serviceInfoVo, ResultHandler<ServiceInfo, ServiceInfoVo> resultHandler) {
        try {
            ServiceInfo existServiceInfo = checkUnique(serviceInfoVo.getId(), serviceInfoVo.getWsdl());
            existServiceInfo.updateValue(serviceInfoVo);
            resultHandler.success(this.serviceInfoRepository.save(existServiceInfo));
        } catch (DomainException e) {
            resultHandler.fail(serviceInfoVo, e.getMessage());
        }
    }

    private ServiceInfo checkUnique(Long id, String wsdl) throws DataNotFoundException, DataExistException {
        ServiceInfo serviceInfo = null;
        if (id != null && id > 0) {
            serviceInfo = this.serviceInfoRepository.findOne(id);
            if (serviceInfo == null) {
                throw new DataNotFoundException();
            }
        }
        if (serviceInfo == null || !serviceInfo.getWsdl().equals(wsdl)) {
            serviceInfo = this.serviceInfoRepository.findByWsdl(wsdl);
            if (serviceInfo != null) {
                throw new DataExistException();
            }
        }
        return serviceInfo;
    }
}
