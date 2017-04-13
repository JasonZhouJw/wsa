package com.alpha.common.view;

import com.alpha.common.model.Message;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.common.view.PropertyResources.LABEL_TEXT_FULL_NAME;

/**
 * Created by jzhou237 on 2017-03-02.
 */
@Setter
@Getter
@PropertySource(LABEL_TEXT_FULL_NAME)
public class BaseModelView extends ModelAndView {

    public static final String MESSAGE_PARAM = "messages";
    @Value("${label.success}")
    protected String success;
    private List<Message> messageList = new ArrayList<>();

    public void success() {
        this.addMessage(this.success);
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

    protected void setMessage(List<Message> messageList) {
        if (CollectionUtils.isEmpty(messageList)) {
            this.success();
        } else {
            this.addObject(MESSAGE_PARAM, messageList);
        }
    }


}
