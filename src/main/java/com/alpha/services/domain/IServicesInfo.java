package com.alpha.services.domain;

import com.alpha.services.entities.ServicesInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-20.
 */
public interface IServicesInfo {

    List<ServicesInfo> findAllActive();

    void findAll(ServicesInfo servicesInfo, Pageable pageable, Consumer<Page<ServicesInfo>> consumer);
}
