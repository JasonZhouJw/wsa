package com.alpha.testcase.view;

import com.alpha.common.view.BaseModelView;
import com.alpha.testcase.entities.CaseGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.alpha.common.controller.Urls.*;

/**
 * Created by jzhou237 on 2017-03-30.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CaseGroupIndex extends BaseModelView {

    public CaseGroupIndex(@Value("${list.empty}") String noDataMessage) {
        this.setViewName(CASE_GROUP_INDEX);
        this.addObject("noDataMessage", noDataMessage);
        this.addObject("updateUrl", CASE_GROUP_TO_UPDATE);
        this.addObject("addUrl", CASE_GROUP_TO_CREATE);
    }

    public void setCaseGroup(List<CaseGroup> caseGroupList) {
        this.addObject("caseGroupList", CaseGroup.toVo(caseGroupList));
    }
}
