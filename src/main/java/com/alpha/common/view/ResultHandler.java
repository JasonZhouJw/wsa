package com.alpha.common.view;

import com.alpha.common.model.Result;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

import static com.alpha.common.utils.Constants.DANGER;

/**
 * Created by jzhou237 on 2017-04-05.
 */
@Getter
@Setter
public class ResultHandler<S, R> {

    private Consumer<Result<S>> successHandler;

    private S successObj;

    private Consumer<Result<R>> failHandler;

    private R failObj;

    private boolean success = true;

    public ResultHandler(Consumer<Result<S>> successHandler, Consumer<Result<R>> fail) {
        this.successHandler = successHandler;
        this.failHandler = fail;
    }

    public void success(S s) {
        this.successObj = s;
        this.successHandler.accept(new Result<S>(s));
    }

    public void success(S s, String message) {
        this.successObj = s;
        this.successHandler.accept(new Result<S>(s, message));
    }

    public void fail(R r, String message) {
        this.success = false;
        this.failObj = r;
        this.failHandler.accept(new Result<R>(r, message, DANGER));
    }

    public void fail(R r, List<String> messageList) {
        this.success = false;
        this.failObj = r;
        this.failHandler.accept(new Result<R>(r, messageList, DANGER));
    }
}
