package com.alpha.services.view;

import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoVo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.SERVICE_INFO_CREATE;
import static com.alpha.common.controller.Urls.SERVICE_INFO_INDEX;

/**
 * Created by jzhou237 on 2017-04-10.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceInfoCreateView extends HandlerModelView<ServiceInfo, ServiceInfoVo> {

    public ServiceInfoCreateView() {
        this.setViewName(SERVICE_INFO_CREATE);
        this.addObject("indexUrl", SERVICE_INFO_INDEX);
    }

    public void setServiceInfo(ServiceInfoVo serviceInfoVo) {
        this.addObject("serviceInfo", serviceInfoVo);
    }

    @Override
    public void success(Result<ServiceInfo> result) {
        this.success();
        this.setServiceInfo(result.getResult().toVo(true));
    }

    @Override
    public void fail(Result<ServiceInfoVo> result) {
        this.setMessage(result.getMessageList());
        this.setServiceInfo(result.getResult());
    }
}
