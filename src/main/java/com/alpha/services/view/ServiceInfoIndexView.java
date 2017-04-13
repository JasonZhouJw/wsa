package com.alpha.services.view;

import com.alpha.common.view.BaseModelView;
import com.alpha.common.view.ModelAndViewCombiner;
import com.alpha.services.entities.ServiceInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2017-04-07.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceInfoIndexView extends BaseModelView implements ModelAndViewCombiner {

    public ServiceInfoIndexView(@Value("${list.empty}") String noDataMessage) {
        addObject("noDataMessage", noDataMessage);
        addObject("updateUrl", SERVICE_INFO_TO_UPDATE);
        addObject("addUrl", SERVICE_INFO_TO_CREATE);
        addObject("search", SERVICE_INFO_SEARCH);
        this.setViewName(SERVICE_INFO_INDEX);
    }

    public void setServicesInfo(List<ServiceInfo> serviceInfoList) {
        this.addObject("serviceInfoList", ServiceInfo.toVo(serviceInfoList, false));
    }

    @Override
    public ModelAndView original() {
        return this;
    }

    public void setSearchCondition(ServiceInfo condition) {
        this.addObject("searchParam", condition.toVo(false));
    }
}
