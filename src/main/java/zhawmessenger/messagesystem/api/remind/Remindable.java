package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;

import java.util.Date;
import java.util.List;

/**
 */
public interface Remindable<R extends ContactProvider> {
    void setReminderDate(Date date);

    Date getReminderDate();

    void setReminderSent(boolean sent);

    boolean isReminderSent();
}
