package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;

import java.util.List;

/**
 */
public interface Reminder<T extends ContactProvider> {
    void setTime(long time);

    long getTime();

    void addReceiver(T contact);

    List<T> getReceivers();
}
