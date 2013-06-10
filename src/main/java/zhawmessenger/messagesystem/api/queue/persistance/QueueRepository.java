package zhawmessenger.messagesystem.api.queue.persistance;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;

import java.util.Collection;
import java.util.List;

/**
 * Simple interface to load a store a message queue.
 */
public interface QueueRepository {

    List<? extends QueuedMessage> find();

    void markSending(QueuedMessage message);

    void markSent(QueuedMessage message);

    QueuedMessage create(Message message);

    QueuedMessage get(Message message);

    void delete(Message message);
}
