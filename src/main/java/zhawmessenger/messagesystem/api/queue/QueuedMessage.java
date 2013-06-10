package zhawmessenger.messagesystem.api.queue;

import zhawmessenger.messagesystem.api.message.Message;

import java.beans.PropertyChangeListener;

/**
 */
public interface QueuedMessage {

    enum MessageState {
        SENDING, SENT
    }

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    MessageState getState();

    boolean isLocked();

    boolean tryLock();

    void release();

    long getTimeQueued();

    void suspend();

    void suspend(long timeSuspendedUntil);

    void resume();

    boolean isSuspended();

    Message getMessage();

    boolean canUnbox(Class<? extends Message> messageClass);

    <T extends Message> T unbox(Class<T> messageClass);
}
