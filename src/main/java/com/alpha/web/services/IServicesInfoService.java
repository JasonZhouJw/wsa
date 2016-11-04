package com.alpha.web.services;

import com.alpha.core.ws.entity.ServicesInfo;

import java.util.List;

/**
 * Created by jzhou237 on 2016-09-30.
 */
public interface IServicesInfoService {

    ServicesInfo findById(Long id);

    ServicesInfo save(ServicesInfo servicesInfo);

    ServicesInfo update(ServicesInfo servicesInfo);

    List<ServicesInfo> find(ServicesInfo servicesInfo);

    List<ServicesInfo> findActive();
}
