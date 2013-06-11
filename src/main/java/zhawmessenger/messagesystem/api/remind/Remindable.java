package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;

import java.util.List;

/**
 */
public interface Remindable<R extends ContactProvider> {
    void addReminder(Reminder<R> reminder);

    void removeReminder(Reminder<R> reminder);

    List<Reminder<R>> getReminders();
}
