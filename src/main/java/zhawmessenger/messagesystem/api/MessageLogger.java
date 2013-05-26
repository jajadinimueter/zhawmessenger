package zhawmessenger.messagesystem.api;

import zhawmessenger.messagesystem.api.transport.SentMessage;

import java.util.Date;

/**
 */
public interface MessageLogger {

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
