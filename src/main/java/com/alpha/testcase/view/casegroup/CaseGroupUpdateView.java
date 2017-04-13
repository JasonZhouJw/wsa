package com.alpha.testcase.view.casegroup;

import com.alpha.common.enums.Active;
import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.testcase.entities.CaseGroup;
import com.alpha.testcase.model.CaseGroupVo;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.CASE_GROUP_UPDATE;

/**
 * Created by jzhou237 on 2017-03-31.
 */
@Getter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CaseGroupUpdateView extends HandlerModelView<CaseGroup, CaseGroupVo> {


    public CaseGroupUpdateView() {
        this.setViewName(CASE_GROUP_UPDATE);
        this.addObject("updateUrl", CASE_GROUP_UPDATE);
        this.addObject("activeList", Active.options());
    }

    public void setCaseGroup(CaseGroup caseGroup) {
        this.addObject("caseGroup", caseGroup.toVo());
    }

    public void success(Result<CaseGroup> result) {
        super.success(result);
        this.setCaseGroup(result.getResult());
    }

    public void fail(Result<CaseGroupVo> result) {
        super.fail(result);
        this.addObject("caseGroup", result.getResult());
    }

}
