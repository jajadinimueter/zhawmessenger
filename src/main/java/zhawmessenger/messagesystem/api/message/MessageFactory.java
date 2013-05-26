package zhawmessenger.messagesystem.api.message;

/**
 */
public interface MessageFactory {
    <T extends Message> T createMessage(Class<T> cls);
}
