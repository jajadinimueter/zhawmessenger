package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface CancelListener {
    void canceled(Message message);
}
