package zhawmessenger.messagesystem.impl.queue;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.NotSentException;
import zhawmessenger.messagesystem.api.queue.QueueChangeListener;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.remind.Remindable;
import zhawmessenger.messagesystem.api.remind.ReminderListener;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.api.queue.persistance.QueueRepository;
import zhawmessenger.messagesystem.impl.util.DefaultTimeProvider;
import zhawmessenger.messagesystem.impl.util.TimeProvider;

import java.util.ArrayList;
import java.util.List;

/**
 */
@SuppressWarnings("UnusedDeclaration")
public class MessageQueueImpl implements MessageQueue {

    private final List<Transport> transports;
    private final TimeProvider timeProvider;
    private final QueueRepository repository;
    private final List<ReminderListener> reminderListeners;
    private final List<QueueChangeListener> queueChangeListeners;

    public MessageQueueImpl(List<Transport> transports,
                            TimeProvider timeProvider,
                            QueueRepository repository) {

        this.queueChangeListeners = new ArrayList<QueueChangeListener>();
        this.reminderListeners = new ArrayList<ReminderListener>();
        this.repository = repository;
        this.transports = transports;
        this.timeProvider = timeProvider;
    }

    @SuppressWarnings("UnusedDeclaration")
    public MessageQueueImpl(List<Transport> transports,
                            QueueRepository repository) {
        this(
                transports,
                new DefaultTimeProvider(),
                repository
        );
    }

    protected List<? extends QueuedMessage> getMessages() {
        return this.repository.find();
    }

    @Override
    public void addQueueChangeListener(QueueChangeListener listener) {
        queueChangeListeners.add(listener);
    }

    public SentMessage send(Message message) {
        return this.send(message, false);
    }

    protected void fireQueueChanged() {
        for (QueueChangeListener l : queueChangeListeners) {
            l.queueChanged(this);
        }
    }

    public SentMessage send(Message msg, boolean force) {
        QueuedMessage message = repository.get(msg);
        try {
            if (message.tryLock()) {
                if (canBeSent(message)) {
                    if (force || this.shouldBeSent(message)) {
                        for (Transport transport : this.transports) {
                            //noinspection unchecked
                            if (transport.canSend(msg.getClass())) {
                                // We can suppress this because
                                // the transport already told us
                                // it can handle this type!
                                repository.markSending(message);
                                //noinspection unchecked
                                SentMessage sentMessage = transport.send(msg);
                                repository.markSent(message);

                                fireQueueChanged();

                                return sentMessage;
                            }
                        }
                        throw new RuntimeException(
                                "No Transport found for message " + message);
                    }
                }
            }
        } finally {
            // can be safely released
            message.release();
        }

        throw new NotSentException(message);
    }

    public void sendAll() {
        this.sendAll(false);
    }

    private boolean canBeSent(QueuedMessage message) {
        return !message.isSuspended() &&
                message.getState() != QueuedMessage.MessageState.SENT &&
                message.getState() != QueuedMessage.MessageState.SENDING;
    }

    private boolean shouldBeSent(QueuedMessage message) {
        if (message.getMessage().getSendDate() == null) {
            return true;
        } else {
            return message.getMessage().getSendDate().getTime()
                    < this.timeProvider.getTime();
        }
    }

    @Override
    public void addReminderListener(ReminderListener listener) {
        reminderListeners.add(listener);
    }

    private void noticeReminder(QueuedMessage message) {
        Object msg = message.getMessage();
        if (msg instanceof Remindable) {
            Message m = (Message) msg;
            Remindable r = (Remindable) msg;
            if (!r.isReminderSent() && r.getReminderDate() != null) {
                if (!message.isSuspended()) {
                    if (r.getReminderDate().getTime() <= timeProvider.getTime()) {
                        for (ReminderListener l : reminderListeners) {
                            l.remind((Message) msg);
                            r.setReminderSent(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Delivers all messages which are
     */
    public void sendAll(boolean force) {
        for (QueuedMessage queuedMessage : getMessages()) {
            // send reminders
            noticeReminder(queuedMessage);
            if (canBeSent(queuedMessage) && shouldBeSent(queuedMessage)) {
                this.send(queuedMessage.getMessage(), force);
            }
        }
    }

    @Override
    public QueuedMessage add(Message message) {
        QueuedMessage q =  repository.create(message);
        fireQueueChanged();
        return q;
    }

    public QueuedMessage get(Message message) {
        return repository.get(message);
    }

    @Override
    public List<? extends QueuedMessage> getQueuedMessages() {
        return getMessages();
    }

    @Override
    public boolean contains(Message message) {
        return repository.get(message) != null;
    }

    @Override
    public void schedule() {
        try {
            this.sendAll();
        } catch (NotSentException e) {
            // FIXME: do something
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Message message) {
        QueuedMessage msg = this.get(message);
        msg.suspend();
        repository.delete(message);
        fireQueueChanged();
    }
}
