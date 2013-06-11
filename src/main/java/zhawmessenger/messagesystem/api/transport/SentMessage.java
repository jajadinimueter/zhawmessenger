package zhawmessenger.messagesystem.api.transport;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.message.Message;

import java.util.Date;
import java.util.List;

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

    List<? extends Contact> getReceivers();

}
