package zhawmessenger.messagesystem.impl.queue;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
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

    /**
     * Delivers all messages which are
     */
    public void deliver() {
        Message message;
        for (QueuedMessage queuedMessage : messages) {
            try {
                if (queuedMessage.tryLock()) {
                    if (!queuedMessage.isSuspended()) {
                        message = queuedMessage.getMessage();
                        if (message.getSendTime() < this.timeProvider.getTime()) {
                            for (Transport transport : this.transports) {
                                if (transport.canSend(message.getClass())) {
                                    // We can suppress this because
                                    // the transport already told us
                                    // it can handle this type!
                                    //noinspection unchecked
                                    transport.send(message);
                                }
                            }
                        }
                    }
                }
            } finally {
                // can be safely released
                queuedMessage.release();
            }
        }
    }

    @Override
    public QueuedMessage add(Message message) {
        QueuedMessage qMessage = new QueuedMessageImpl(message, timeProvider);
        messages.add(qMessage);
        return qMessage;
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

}
