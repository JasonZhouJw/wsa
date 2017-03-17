package com.alpha.verifyresult.view;

import com.alpha.common.view.BaseModelView;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.VERIFY_RESULT_INDEX;

/**
 * Created by jzhou237 on 2017-03-13.
 */
@Component("verifyResultIndexView")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndexView extends BaseModelView {

    public IndexView() {
        this.setViewName(VERIFY_RESULT_INDEX);
    }
}
