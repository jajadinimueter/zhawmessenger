package zhawmessenger.messagesystem.impl.remind;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.remind.Reminder;

import java.util.List;

/**
 */
public class ReminderImpl<T extends Contact> implements Reminder<T> {

    @Override
    public void setTime(long time) {
        // FIXME
    }

    @Override
    public long getTime() {
        return 0;  // FIXME
    }

    @Override
    public void addReceiver(T contact) {
        // FIXME
    }

    @Override
    public List<T> getReceivers() {
        return null;  // FIXME
    }
}
