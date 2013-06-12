package zhawmessenger.messagesystem.api.remind;

import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface ReminderListener {
    void remind(Message message);
}
