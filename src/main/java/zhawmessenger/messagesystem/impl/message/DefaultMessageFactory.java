package zhawmessenger.messagesystem.impl.message;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.impl.modules.email.message.EmailImpl;

/**
 */
public class DefaultMessageFactory implements MessageFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Message> T createMessage(Class<T> cls) {
        if (Email.class.isAssignableFrom(cls)) {
            return (T) new EmailImpl();
        }
        throw new NotImplementedException();
    }
}
