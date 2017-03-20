package com.alpha.services.domain;

import com.alpha.services.entities.ServicesInfo;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-20.
 */
public interface IServicesInfo {

    List<ServicesInfo> findAllActive();
}
