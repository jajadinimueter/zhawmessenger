package zhawmessenger.messagesystem.api.util;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.transport.SentMessage;

import java.util.Date;

/**
 * The SentMessageLogger is used in {@link zhawmessenger.messagesystem.api.transport.Transport}S
 * to log when {@link Message}S are sent.
 */
public interface SentMessageLogger {

    /**
     * Use this logger when you did send a message.
     *
     * @param message the message object
     * @param sendAt when did you send the message?
     */
    void log(Message message, Date sendAt);

    /**
     * Use this logger when you did send a message.
     *
     * @param message the message object
     */
    void log(SentMessage message);

}
