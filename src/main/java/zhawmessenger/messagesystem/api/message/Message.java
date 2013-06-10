package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.persistance.IdObject;

import java.util.List;

/**
 */
public interface Message<R extends Contact> extends IdObject {

    /**
     * Returns an ID with uniqually identifies
     * this message
     *
     * @return an ID
     */
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
