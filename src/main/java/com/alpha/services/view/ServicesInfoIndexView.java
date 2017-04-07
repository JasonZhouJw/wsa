package com.alpha.services.view;

import com.alpha.common.view.BaseModelView;
import com.alpha.common.view.ModelAndViewCombiner;
import com.alpha.services.entities.ServicesInfo;
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
public class ServicesInfoIndexView extends BaseModelView implements ModelAndViewCombiner {

    public ServicesInfoIndexView(@Value("${list.empty}") String noDataMessage) {
        addObject("noDataMessage", noDataMessage);
        addObject("updateUrl", TEST_CASE_TO_UPDATE);
        addObject("addUrl", TEST_CASE_TO_CREATE);
        addObject("search", TEST_CASE_SEARCH);
        this.setViewName(SERVICES_INFO_INDEX);
    }

    public void setServicesInfo(List<ServicesInfo> servicesInfoList) {
        this.addObject("servicesInfoList", ServicesInfo.toVo(servicesInfoList, false));
    }

    @Override
    public ModelAndView original() {
        return this;
    }

    public void setSearchCondition(ServicesInfo condition) {
        this.addObject("searchParam", condition.toVo(false));
    }
}
