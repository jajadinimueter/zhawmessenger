package zhawmessenger.messagesystem.util;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.util.SentMessageLogger;
import zhawmessenger.messagesystem.api.transport.SentMessage;

import java.util.Date;

/**
 */
public class ConsoleSentMessageLogger implements SentMessageLogger {
    @Override
    public void log(Message message, Date sendAt) {
        System.out.println(String.format("Message %s sent at %s", message, sendAt));
    }

    @Override
    public void log(SentMessage message) {
        System.out.println(String.format("Message %s sent", message));
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
