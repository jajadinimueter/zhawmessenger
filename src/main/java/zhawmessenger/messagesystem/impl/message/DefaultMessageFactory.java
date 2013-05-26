package zhawmessenger.messagesystem.impl.message;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;

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
