package zhawmessenger.messagesystem.api.queue;

import ca.odell.glazedlists.EventList;
import zhawmessenger.messagesystem.api.Message;

import java.util.Date;

/**
 */
public interface QueuedMessage {

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
