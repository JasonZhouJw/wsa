package com.alpha.common.view;

import com.alpha.common.model.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.common.utils.Constants.DANGER;
import static com.alpha.common.utils.Constants.WARNING;
import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;

/**
 * Created by jzhou237 on 2017-03-02.
 */
@Setter
@Getter
@PropertySource(LABEL_TEXT_FULL_NAME)
public abstract class BaseModelView extends ModelAndView {

    public static final String MESSAGE_PARAM = "messages";

    private boolean hasError = false;

    @Value("${label.success}")
    protected String success;

    private List<Message> messageList = new ArrayList<>();

    public BaseModelView success() {
        this.addMessage(this.success);
        return this;
    }

    public void addMessage(String message) {
        setMessage(new Message(message));
    }

    public void addWarning(String message) {
        setMessage(new Message(message, WARNING));
        hasError = true;
    }

    public void addDanger(String message) {
        setMessage(new Message(message, DANGER));
        hasError = true;
    }

    protected void setMessage(Message message) {
        List<Message> messageList = (List<Message>) this.getModelMap().get(MESSAGE_PARAM);
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        this.addObject(MESSAGE_PARAM, messageList);
    }

    protected void setMessage(List<Message> messageList) {
        this.addObject(MESSAGE_PARAM, messageList);
    }

    public boolean hasError() {
        return hasError;
    }

}
