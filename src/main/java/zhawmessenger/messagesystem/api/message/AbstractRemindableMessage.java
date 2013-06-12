package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.remind.Remindable;
import zhawmessenger.messagesystem.api.remind.Reminder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 */
public abstract class AbstractRemindableMessage<R extends Contact>
        extends AbstractMessage<R> implements Remindable<R> {

    private Date reminderDate;

    public AbstractRemindableMessage(Object id) {
        super(id);
    }

    @Override
    public void setReminderDate(Date date) {
        reminderDate = date;
    }

    @Override
    public Date getReminderDate() {
        return reminderDate;
    }
}
