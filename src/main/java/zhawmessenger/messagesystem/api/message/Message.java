package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.EmailContact;

import java.util.List;

/**
 */
public interface Message<R extends Contact> {
    long getSendTime();

    void setSendTime(long time);

    String getText();

    void setText(String text);

    void validate();

    boolean isValid();

    void addReceiver(R receiver);

    void removeReceiver(R receiver);

    List<R> getReceivers();
}
