package zhawmessenger.messagesystem.api.remind;

import java.util.List;

/**
 */
public interface Remindable {
    void addReminder(Reminder reminder);

    void removeReminder(Reminder reminder);

    List<Reminder> getReminders();
}
