package com.alpha.testcase.view;

import com.alpha.common.enums.Active;
import com.alpha.common.view.BaseModelView;
import com.alpha.common.view.ModelAndViewCombiner;
import com.alpha.testcase.entities.TestCase;
import com.alpha.testcase.model.TestCaseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.alpha.common.controller.Urls.TEST_CASE_INDEX;
import static com.alpha.common.controller.Urls.TEST_CASE_TO_UPDATE;

/**
 * Created by jzhou237 on 2017-03-16.
 */
@Component("testCaseIndexView")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndexView extends BaseModelView implements ModelAndViewCombiner {

    public IndexView(@Value("${list.empty}") String noDataMessage) {
        addObject("noDataMessage", noDataMessage);
        addObject("updateUrl", TEST_CASE_TO_UPDATE);
        this.setViewName(TEST_CASE_INDEX);
        this.searchCondition();
    }

    private void searchCondition() {
        this.addObject("activeList", Active.options());
    }

    public void addTestCase(List<TestCase> content) {
        this.addObject("testCaseList", TestCase.toVo(content));
    }

    public void addSearchParam(TestCaseVo testCaseVo) {
        this.addObject("searchParam", testCaseVo);
        this.addObject("activeList", Active.options());
    }

    @Override
    public ModelAndView original() {
        return this;
    }
}
