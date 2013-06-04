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
        System.out.println("Message sent!");
    }

    @Override
    public void log(SentMessage message) {
        System.out.println("Message sent!");
    }
}
