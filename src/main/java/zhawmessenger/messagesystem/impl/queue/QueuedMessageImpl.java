package zhawmessenger.messagesystem.impl.queue;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.impl.util.DefaultTimeProvider;
import zhawmessenger.messagesystem.impl.util.TimeProvider;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 */
public class QueuedMessageImpl implements QueuedMessage {

    private MessageState state;
    private boolean sent = false;
    private long timeQueued = 0;
    private long timeSuspendedUntil = 0;
    private ReentrantLock lock = new ReentrantLock();

    private boolean suspended = false;
    private TimeProvider timeProvider;
    private Message message;

    @SuppressWarnings("UnusedDeclaration")
    public QueuedMessageImpl(Message message) {
        this(message, new DefaultTimeProvider());
    }

    public QueuedMessageImpl(Message message, TimeProvider timeProvider) {
        this.message = message;
        this.timeProvider = timeProvider;
        this.timeQueued = new Date().getTime();
    }

    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    @Override
    public boolean isLocked() {
        return this.lock.isLocked();
    }

    @Override
    public boolean tryLock() {
        return this.lock.tryLock();
    }

    @Override
    public void release() {
        if (this.tryLock()) {
            this.lock.unlock();
        }
    }

    @Override
    public long getTimeQueued() {
        return this.timeQueued;
    }

    @Override
    public void suspend() {
        this.suspended = true;
        this.timeSuspendedUntil = 0;
    }

    @Override
    public void suspend(long timeSuspendedUntil) {
        this.suspend();
        this.timeSuspendedUntil = timeSuspendedUntil;
    }

    @Override
    public boolean isSuspended() {
        return this.suspended && !(this.timeSuspendedUntil
                <timeProvider.getTime());
    }

    @Override
    public void resume() {
        this.suspended = false;
        this.timeSuspendedUntil = 0;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public boolean canUnbox(Class<? extends Message> messageClass) {
        return messageClass.isInstance(this.message);
    }

    @Override
    public <T extends Message> T unbox(Class<T> messageClass) {
        return messageClass.cast(this.message);
    }
}
