package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.impl.modules.email.message.EmailImpl;
import zhawmessenger.ui.api.*;

import javax.swing.*;

/**
 */
public class EmailMessagePlugin implements MessagePlugin<Email> {

    @SuppressWarnings("unchecked")
    @Override
    public Class<Email> getMessageClass() {
        return Email.class;
    }

    @Override
    public boolean doesHandle(Class<Email> messageClass) {
        return Email.class.isAssignableFrom(messageClass);
    }

    public String getName() {
        return "Email";
    }

    public Icon getIcon() {
        return null;
    }

    @Override
    public MessageFactory getMessageFactory() {
        return new MessageFactory() {
            @Override
            public Message createMessage() {
                return new EmailImpl();
            }
        };
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new EmailFormFactory();
    }

    public MessageWindowFactory getWindowFactory() {
        return new DefaultMessageWindowFactory();
    }

}
