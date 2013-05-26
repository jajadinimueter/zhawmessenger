package zhawmessenger.messagesystem.impl.transport;

import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.messagesystem.api.MessageLogger;
import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.api.transport.TransportException;

import java.util.Date;

/**
 */
public class EmailTransport implements Transport<Email> {

    private MessageLogger logger;

    public EmailTransport(MessageLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean canSend(Class<? extends Message> messageClass) {
        return Email.class.isAssignableFrom(messageClass);
    }

    @Override
    public SentMessage<Email> send(final Email message) throws TransportException {
        final Date sentAt = new Date();
        return new SentMessage<Email>() {
            @Override
            public Email getMessage() {
                return message;
            }

            @Override
            public Date sentAt() {
                return sentAt;
            }
        };
    }

}
