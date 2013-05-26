package zhawmessenger.messagesystem.util;

import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.messagesystem.api.MessageLogger;
import zhawmessenger.messagesystem.api.transport.SentMessage;

import java.util.Date;

/**
 */
public class ConsoleMessageLogger implements MessageLogger {
    @Override
    public void log(Message message, Date sendAt) {
        System.out.println("Message sent!");
    }

    @Override
    public void log(SentMessage message) {
        System.out.println("Message sent!");
    }
}
