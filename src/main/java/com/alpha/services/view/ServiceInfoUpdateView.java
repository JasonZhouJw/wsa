package com.alpha.services.view;

import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.services.model.ServiceInfoVo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2017-04-11.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceInfoUpdateView extends HandlerModelView<ServiceInfo, ServiceInfoVo> {

    public ServiceInfoUpdateView() {
        this.setViewName(SERVICE_INFO_UPDATE);
        this.addObject("indexUrl", SERVICE_INFO_INDEX);
        this.addObject("assembleUrl", SERVICE_INFO_ASSEMBLE);
    }

    public void setServiceInfo(ServiceInfoVo serviceInfoVo) {
        this.addObject("serviceInfo", serviceInfoVo);
    }

    @Override
    public void success(Result<ServiceInfo> result) {
        super.success(result);
        this.setServiceInfo(result.getResult().toVo(true));
    }

    @Override
    public void fail(Result<ServiceInfoVo> result) {
        super.fail(result);
        this.setServiceInfo(result.getResult());
    }
}
