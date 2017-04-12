package com.alpha.services.domain;

import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.common.view.ResultHandler;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoUpdateVo;
import com.alpha.services.model.ServiceInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jzhou237 on 2017-03-20.
 */
public interface IServiceInfo {

    List<ServiceInfo> findAllActive();

    void findAll(ServiceInfo serviceInfo, Pageable pageable, Consumer<Page<ServiceInfo>> consumer);

    void create(ServiceInfoVo serviceInfoVo, ResultHandler<ServiceInfo, ServiceInfoVo> resultHandler);

    void assemble(Long id, String serviceName);

    ServiceInfo findOne(Long id) throws DataNotFoundException;

    void update(ServiceInfoUpdateVo serviceInfoVo, ResultHandler<ServiceInfo, ServiceInfoVo> resultHandler);
}
