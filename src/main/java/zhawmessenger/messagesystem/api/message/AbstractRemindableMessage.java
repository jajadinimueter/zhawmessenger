package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.remind.Remindable;
import zhawmessenger.messagesystem.api.remind.Reminder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public abstract class AbstractRemindableMessage<R extends EmailContact>
        extends AbstractMessage<R> implements Remindable<R> {

    private final List<Reminder<R>> reminders;

    public AbstractRemindableMessage(Object id) {
        super(id);
        reminders = new ArrayList<Reminder<R>>();
    }

    @Override
    public void addReminder(Reminder<R> reminder) {
        reminders.add(reminder);
    }

    @Override
    public void removeReminder(Reminder<R> reminder) {
        reminders.remove(reminder);
    }

    @Override
    public List<Reminder<R>> getReminders() {
        return Collections.unmodifiableList(reminders);
    }
}
