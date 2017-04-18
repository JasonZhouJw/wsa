package com.alpha.verifyresult.view;

import com.alpha.common.enums.ResultType;
import com.alpha.common.view.BaseModelView;
import com.alpha.common.view.ModelAndViewCombiner;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultSearchVo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.alpha.common.controller.Urls.VERIFY_RESULT_INDEX;
import static com.alpha.common.controller.Urls.VERIFY_RESULT_TO_UPDATE;

/**
 * Created by jzhou237 on 2017-03-13.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VerifyResultIndexView extends BaseModelView implements ModelAndViewCombiner {

    public VerifyResultIndexView() {
        this.setViewName(VERIFY_RESULT_INDEX);
        this.addObject("toUpdateUrl", VERIFY_RESULT_TO_UPDATE);
        this.init();
    }

    private void init() {
        this.addObject("resultType", ResultType.options());
    }

    public void setVerifyResult(List<VerifyResult> verifyResultList) {
        this.addObject("verifyResultList", VerifyResult.toVo(verifyResultList));
    }

    @Override
    public ModelAndView original() {
        return this;
    }

    public void setSearchParam(VerifyResultSearchVo verifyResultVo) {
        this.addObject("searchParam", verifyResultVo);
    }
}
