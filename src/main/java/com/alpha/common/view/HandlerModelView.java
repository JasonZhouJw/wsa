package com.alpha.common.view;

import com.alpha.common.model.Result;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by jzhou237 on 2017-04-06.
 */
@Getter
@Setter
public class HandlerModelView<S, R> extends BaseModelView {

    protected ResultHandler<S, R> resultHandler = new ResultHandler<S, R>(this::success, this::fail);

    public HandlerModelView() {

    }

    public void success(Result<S> result) {
        if (!resultHandler.isNoMsg()) {
            setMessage(result.getMessageList());
        }
    }

    public void fail(Result<R> result) {
        if (!resultHandler.isNoMsg() && CollectionUtils.isNotEmpty(result.getMessageList())) {
            setMessage(result.getMessageList());
        }
    }
}
