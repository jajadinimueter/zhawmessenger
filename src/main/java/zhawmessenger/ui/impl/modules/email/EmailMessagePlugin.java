package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.ui.api.*;

import javax.swing.*;

/**
 */
public class EmailMessagePlugin implements MessagePlugin<Email> {

    @Override
    public Class<? extends Message> getMessageClass() {
        return Email.class;
    }

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
