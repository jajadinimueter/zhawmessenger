package zhawmessenger.messagesystem.impl.message;

import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;

/**
 */
public class DefaultMessageFactory implements MessageFactory {
    @Override
    public <T extends Message> T createMessage(Class<? extends Message> cls) {
        if (Email.class.isAssignableFrom(cls)) {
            return new EmailImpl();
        }
    }
}
