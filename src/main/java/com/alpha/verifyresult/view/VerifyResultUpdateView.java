package com.alpha.verifyresult.view;

import com.alpha.common.model.Result;
import com.alpha.common.view.HandlerModelView;
import com.alpha.verifyresult.entities.VerifyResult;
import com.alpha.verifyresult.model.VerifyResultVo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static com.alpha.common.controller.Urls.VERIFY_RESULT_INDEX;
import static com.alpha.common.controller.Urls.VERIFY_RESULT_UPDATE;

/**
 * Created by jzhou237 on 2017-04-17.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VerifyResultUpdateView extends HandlerModelView<VerifyResult, VerifyResultVo> {

    public VerifyResultUpdateView() {
        this.setViewName(VERIFY_RESULT_UPDATE);
        this.addObject("indexUrl", VERIFY_RESULT_INDEX);
    }

    public void setVerifyResult(VerifyResultVo verifyResultVo) {
        this.addObject("verifyResult", verifyResultVo);
    }

    @Override
    public void success(Result<VerifyResult> result) {
        this.setVerifyResult(result.getResult().toVo());
    }

    @Override
    public void fail(Result<VerifyResultVo> result) {
        this.setMessage(result.getMessageList());
        this.setVerifyResult(result.getResult());
    }
}
