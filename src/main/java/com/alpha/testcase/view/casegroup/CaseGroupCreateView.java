package com.alpha.testcase.view.casegroup;

import com.alpha.common.enums.Active;
import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CaseGroupVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_CREATE;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@Setter
@Getter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CaseGroupCreateView extends HandlerModelView<CaseGroup, CaseGroupVo> {

    public CaseGroupCreateView() {
        this.setViewName(CASE_GROUP_CREATE);
        this.addObject("activeList", Active.options());
    }

    public void success(Result<CaseGroup> result) {
        super.success(result);
        this.setCaseGroup(result.getResult());
    }

    public void fail(Result<CaseGroupVo> result) {
        super.fail(result);
        this.addObject("caseGroup", result.getResult());
    }


    public void setCaseGroup(CaseGroup caseGroup) {
        this.addObject("caseGroup", caseGroup.toVo());
    }

}
