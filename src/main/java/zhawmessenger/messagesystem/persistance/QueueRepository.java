package zhawmessenger.messagesystem.persistance;

import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;

import java.util.Collection;

/**
 * Simple interface to load a store a message queue.
 */
public interface QueueRepository {

    Collection<QueuedMessage> find();

    void markSending(QueuedMessage message);

    void markSent(QueuedMessage message);

    void store(QueuedMessage message);
}
