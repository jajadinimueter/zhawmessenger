package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;

import java.util.List;

/**
 */
public interface Remindable<R extends Contact> {
    void addReminder(Reminder<R> reminder);

    void removeReminder(Reminder<R> reminder);

    List<Reminder<R>> getReminders();
}
