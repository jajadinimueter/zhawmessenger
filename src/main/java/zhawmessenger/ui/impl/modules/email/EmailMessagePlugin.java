package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.action.AbstractCreateAction;

import javax.swing.*;

/**
 */
public class EmailMessagePlugin implements MessagePlugin<Email> {

    @Override
    public boolean doesHandle(Class<? extends Message> messageClass) {
        return Email.class.isAssignableFrom(messageClass);
    }

    public String getName() {
        return "Email";
    }

    public Icon getIcon() {
        return null;
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new EmailCreateFormFactory();
    }

}
