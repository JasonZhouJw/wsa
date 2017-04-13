package com.alpha.common.view;

import com.alpha.common.model.Result;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * Created by jzhou237 on 2017-04-06.
 */
@Getter
@Setter
@Component
public class HandlerModelView<S, R> extends BaseModelView {

    protected ResultHandler<S, R> resultHandler = new ResultHandler<S, R>(this.success, this::success, this::fail);


    public void success(Result<S> result) {
        if (CollectionUtils.isNotEmpty(result.getMessageList())) {
            setMessage(result.getMessageList());
        }
    }

    public void fail(Result<R> result) {
        if (CollectionUtils.isNotEmpty(result.getMessageList())) {
            setMessage(result.getMessageList());
        }
    }
}
