package zhawmessenger.messagesystem.impl.modules.email.transport;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.email.transport.EmailTransport;
import zhawmessenger.messagesystem.api.util.SentMessageLogger;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.TransportException;

import java.util.Date;

/**
 */
public class EmailTransportImpl implements EmailTransport {

    private SentMessageLogger logger;

    public EmailTransportImpl(SentMessageLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean canSend(Class<? extends Message<?>> messageClass) {
        return Email.class.isAssignableFrom(messageClass);
    }

    @Override
    public SentMessage<Email> send(final Email message) throws TransportException {
        final Date sentAt = new Date();
        SentMessage<Email> sm = new SentMessage<Email>() {
            @Override
            public Email getMessage() {
                return message;
            }

            @Override
            public Date sentAt() {
                return sentAt;
            }
        };
        logger.log(sm);
        return sm;
    }

}
