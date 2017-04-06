package com.alpha.common.view;

import com.alpha.common.model.Result;

import java.util.List;
import java.util.function.Consumer;

import static com.alpha.common.utils.Constants.DANGER;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class ResultHandler<S, R> {

    private Consumer<Result<S>> success;

    private Consumer<Result<R>> fail;

    public ResultHandler(Consumer<Result<S>> success, Consumer<Result<R>> fail) {
        this.success = success;
        this.fail = fail;
    }

    public void success(S s) {
        this.success.accept(new Result<S>(s));
    }

    public void success(S s, String message) {
        this.success.accept(new Result<S>(s, message));
    }

    public void fail(R r, String message) {
        this.fail.accept(new Result<R>(r, message, DANGER));
    }

    public void fail(R r, List<String> messageList) {
        this.fail.accept(new Result<R>(r, messageList, DANGER));
    }
}
