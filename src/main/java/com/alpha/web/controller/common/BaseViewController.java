package com.alpha.web.controller.common;

import com.alpha.core.ws.utils.ILog;
import org.springframework.ui.ModelMap;

/**
 * Created by jzhou237 on 2016-11-18.
 */
public interface BaseViewController extends ILog {

    void initView(ModelMap modelMap);

}
