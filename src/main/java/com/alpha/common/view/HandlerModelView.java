package com.alpha.common.view;

import com.alpha.common.model.Result;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jzhou237 on 2017-04-06.
 */
@Getter
@Setter
public class HandlerModelView<S, R> extends BaseModelView {

    protected ResultHandler<S, R> resultHandler = new ResultHandler<S, R>(this::success, this::fail);


    public void success(Result<S> result) {

    }

    public void fail(Result<R> result) {

    }
}
