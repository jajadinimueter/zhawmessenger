package zhawmessenger.messagesystem.impl.modules.mobilephone.transport;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.ShortMessage;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.api.transport.TransportException;
import zhawmessenger.messagesystem.api.util.SentMessageLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 */
public class FakeMobilePhoneTransport implements Transport<ShortMessage> {
    private SentMessageLogger logger;

    public FakeMobilePhoneTransport(SentMessageLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean canSend(Class<? extends Message<?>> messageClass) {
        // we can handle SMS and MMS
        return ShortMessage.class.isAssignableFrom(messageClass);
    }

    @Override
    public SentMessage<ShortMessage> send(final ShortMessage message) throws TransportException {
        final Date sentAt = new Date();
        for (MobilePhoneContact contact : message.getReceivers()) {
            logger.log(String.format("Sent SMS to %s with text %s and bytes %s",
                    contact, message.getText(), message.getBytes()));
        }
        return new SentMessage<ShortMessage>() {
            @Override
            public ShortMessage getMessage() {
                return message;
            }

            @Override
            public Date sentAt() {
                return sentAt;
            }

            @Override
            public List<? extends Contact> getReceivers() {
                return null;  // FIXME
            }
        };
    }
}
