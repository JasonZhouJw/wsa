package com.alpha.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-04-05.
 */
@Setter
@Getter
public class Result<T> {

    private List<Message> messageList = new ArrayList<>();

    private T result;

    private boolean success = false;

    public Result(T t) {
        this.result = t;
        success = true;
    }

    public Result(T t, String message) {
        this.result = t;
        this.messageList.add(new Message(message));
    }

    public Result(T t, List<String> messageList, String type) {
        this.result = t;
        messageList.forEach(message -> {
            this.messageList.add(new Message(message, type));
        });
    }

}
