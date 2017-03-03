package com.alpha.common.view;

import com.alpha.common.model.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhou237 on 2017-03-02.
 */
@Setter
@Getter
public abstract class BaseModelView extends ModelAndView {

    public static final String MESSAGE_PARAM = "messages";

    private List<Message> messageList = new ArrayList<>();

    public void addMessage(String message, String type) {
        setMessage(new Message(message, type));
    }

    public void addMessage(String message) {
        setMessage(new Message(message));
    }

    protected void setMessage(Message message) {
        List<Message> messageList = (List<Message>) this.getModelMap().get(MESSAGE_PARAM);
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        this.addObject(MESSAGE_PARAM, messageList);
    }

}
