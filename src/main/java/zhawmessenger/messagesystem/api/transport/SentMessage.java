package zhawmessenger.messagesystem.api.transport;

import zhawmessenger.messagesystem.api.message.Message;

import java.util.Date;

/**
 */
public interface SentMessage<T extends Message> {
    /**
     * Returns which message was sent.
     * @return
     */
    T getMessage();

    /**
     * Returns the date it was sent
     * @return
     */
    Date sentAt();
}
