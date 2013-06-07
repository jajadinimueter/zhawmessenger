package zhawmessenger.messagesystem.impl.queue;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.util.DefaultTimeProvider;
import zhawmessenger.messagesystem.util.TimeProvider;

import java.util.List;

/**
 */
@SuppressWarnings("UnusedDeclaration")
public class MessageQueueImpl implements MessageQueue {

    private final List<Transport> transports;
    private final EventList<QueuedMessage> messages;
    private final TimeProvider timeProvider;

    public MessageQueueImpl(List<Transport> transports,
                            TimeProvider timeProvider,
                            EventList<QueuedMessage> messages) {
        this.messages = messages;
        this.transports = transports;
        this.timeProvider = timeProvider;
    }

    public MessageQueueImpl(List<Transport> transports,
                            TimeProvider timeProvider) {
        this(
                transports,
                timeProvider,
                new BasicEventList<QueuedMessage>()
        );
    }

    @SuppressWarnings("UnusedDeclaration")
    public MessageQueueImpl(List<Transport> transports) {
        this(
                transports,
                new DefaultTimeProvider(),
                new BasicEventList<QueuedMessage>()
        );
    }

    public void send(QueuedMessage message) {
        this.send(message, false);
    }

    public SentMessage send(QueuedMessage message, boolean force) {
        try {
            if (message.tryLock()) {
                if (!message.isSuspended()) {
                    Message msg = message.getMessage();
                    if (force || msg.getSendTime() < this.timeProvider.getTime()) {
                        for (Transport transport : this.transports) {
                            if (transport.canSend(msg.getClass())) {
                                // We can suppress this because
                                // the transport already told us
                                // it can handle this type!
                                //noinspection unchecked
                                return transport.send(msg);
                            }
                        }
                    }
                }
            }
        } finally {
            // can be safely released
            message.release();
        }

        throw new RuntimeException("Message cannot be sent");
    }

    public void sendAll() {
        this.sendAll(false);
    }

    /**
     * Delivers all messages which are
     */
    public void sendAll(boolean force) {
        for (QueuedMessage queuedMessage : messages) {
            this.send(queuedMessage, force);
        }
    }

    @Override
    public QueuedMessage add(Message message) {
        if (this.contains(message)) {
            return this.get(message);
        } else {
            QueuedMessage qMessage = new QueuedMessageImpl(message, timeProvider);
            messages.add(qMessage);
            return qMessage;
        }
    }

    public QueuedMessage get(Message message) {
        for (QueuedMessage m : messages) {
            if (m.getMessage() == message) {
                return m;
            }
        }
        return null;
    }

    @Override
    public QueuedMessage get(int index) {
        return messages.get(index);
    }

    @Override
    public QueuedMessage getById(long id) {
        throw new NotImplementedException();
    }

    @Override
    public EventList<QueuedMessage> getQueuedMessages() {
        return messages;
    }

    @Override
    public boolean contains(QueuedMessage message) {
        return this.messages.contains(message);
    }

    public boolean contains(Message message) {
        for (QueuedMessage m : this.messages) {
            if (m.getMessage().equals(message)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void schedule() {
        this.sendAll();
    }
}
