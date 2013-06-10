package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;

import java.util.List;

/**
 */
public interface Reminder<T extends Contact> {
    void setTime(long time);

    long getTime();

    void addReceiver(T contact);

    List<T> getReceivers();
}
