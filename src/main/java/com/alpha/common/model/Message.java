package com.alpha.common.model;

import com.alpha.common.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Setter
@Getter
public class Message {

    private String message;

    private String type;

    private List<Message> messages = new ArrayList<>();

    public Message() {

    }

    public Message(String message) {
        this(message, Constants.INFO);
    }


    public Message(String message, String type) {
        this.message = message;
        this.type = type;
    }

}
