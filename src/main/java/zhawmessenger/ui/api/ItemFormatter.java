package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface ItemFormatter<T> {
    String format(T message);
}
