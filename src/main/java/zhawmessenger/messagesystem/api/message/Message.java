package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;

import java.util.List;

/**
 */
public interface Message<R extends Contact> {

    /**
     * Returns an ID with uniqually identifies
     * this message
     *
     * @return an ID
     */
    Object getId();

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
