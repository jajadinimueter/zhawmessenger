package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;

import java.util.List;

/**
 */
public interface Reminder {
    void setTime(long time);

    long getTime();

    void addReceiver(Contact contact);

    List<Contact> getReceivers();
}
