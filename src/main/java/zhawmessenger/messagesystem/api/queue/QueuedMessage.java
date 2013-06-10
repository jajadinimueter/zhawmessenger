package zhawmessenger.messagesystem.api.queue;

import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface QueuedMessage {

    boolean isSent();

    void setSent(boolean sent);

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
