package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.Message;

/**
 */
public interface SaveObservable {
    void addSaveListener(SaveListener listener);
}
