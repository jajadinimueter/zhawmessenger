package zhawmessenger.messagesystem.impl.queue;

import ca.odell.glazedlists.EventList;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.NotSentException;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.api.persistance.QueueRepository;
import zhawmessenger.messagesystem.impl.util.DefaultTimeProvider;
import zhawmessenger.messagesystem.impl.util.TimeProvider;

import java.util.Collection;
import java.util.List;

/**
 */
@SuppressWarnings("UnusedDeclaration")
public class MessageQueueImpl implements MessageQueue {

    private final List<Transport> transports;
    private final TimeProvider timeProvider;
    private final QueueRepository repository;

    public MessageQueueImpl(List<Transport> transports,
                            TimeProvider timeProvider,
                            QueueRepository repository) {

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

    protected Collection<? extends QueuedMessage> getMessages() {
        return this.repository.find();
    }

    public SentMessage send(Message message) {
        return this.send(message, false);
    }

    public SentMessage send(Message msg, boolean force) {
        QueuedMessage message = repository.get(msg);
        try {
            if (message.tryLock()) {
                if (canBeSent(message)) {
                    if (force || msg.getSendTime() < this.timeProvider.getTime()) {
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
                                return sentMessage;
                            }
                        }
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

    /**
     * Delivers all messages which are
     */
    public void sendAll(boolean force) {
        for (QueuedMessage queuedMessage : getMessages()) {
            if (canBeSent(queuedMessage)) {
                this.send(queuedMessage.getMessage(), force);
            }
        }
    }

    @Override
    public QueuedMessage add(Message message) {
        return repository.create(message);
    }

    public QueuedMessage get(Message message) {
        return repository.get(message);
    }

    @Override
    public Collection<? extends QueuedMessage> getQueuedMessages() {
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
    }
}
