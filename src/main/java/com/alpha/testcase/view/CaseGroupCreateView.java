package com.alpha.testcase.view;

import com.alpha.common.enums.Active;
import com.alpha.common.view.BaseModelView;
import com.alpha.testcase.entities.CaseGroup;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_CREATE;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CaseGroupCreateView extends BaseModelView {

    public CaseGroupCreateView() {
        this.setViewName(CASE_GROUP_CREATE);
        this.addObject("activeList", Active.options());
    }

    public void setCaseGroup(CaseGroup caseGroup) {
        this.addObject("caseGroup", caseGroup.toVo());
    }

    public BaseModelView success(CaseGroup caseGroup) {
        super.success();
        this.setCaseGroup(caseGroup);
        return this;
    }
}
