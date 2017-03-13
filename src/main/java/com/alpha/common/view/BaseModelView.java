package com.alpha.common.view;

import com.alpha.common.model.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.common.utils.Constants.DANGER;
import static com.alpha.common.utils.Constants.WARNING;

/**
 * Created by jzhou237 on 2017-03-02.
 */
@Setter
@Getter
public abstract class BaseModelView extends ModelAndView {

    public static final String MESSAGE_PARAM = "messages";

    private boolean success = true;

    private List<Message> messageList = new ArrayList<>();

    public void addMessage(String message) {
        setMessage(new Message(message));
    }

    public void addWarning(String message) {
        setMessage(new Message(message, WARNING));
        success = false;
    }

    public void addDanger(String message) {
        setMessage(new Message(message, DANGER));
        success = false;
    }

    protected void setMessage(Message message) {
        List<Message> messageList = (List<Message>) this.getModelMap().get(MESSAGE_PARAM);
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        this.addObject(MESSAGE_PARAM, messageList);
    }

    public boolean hasError() {
        return success;
    }

}
