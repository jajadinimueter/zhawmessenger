package zhawmessenger.messagesystem.persistance;

import zhawmessenger.messagesystem.api.queue.MessageQueue;

/**
 * Simple interface to load a store a message queue.
 */
public interface QueueRepository {

    void persistQueue(MessageQueue queue);

    void loadQueue();

}
