package zhawmessenger.messagesystem.api.queue;

import ca.odell.glazedlists.EventList;
import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface MessageQueue {

    /**
     * Deliver pending messages
     */
    void deliver();

    /**
     * Add a message and receive a queued
     * message wrapper.
     *
     * @param message The message to add
     * @return a queued message wrapper
     */
    QueuedMessage add(Message message);

    /**
     * Get by index inside queue
     *
     * @param index the numeric index. 0-based
     * @return a queued message if found, null when
     *      not found.
     */
    QueuedMessage get(int index);

    /**
     * Get by message-id
     *
     * @param id the message id to search for
     * @return a queued message if found, null when
     *      not found.
     */
    QueuedMessage getById(long id);


    EventList<QueuedMessage> getQueuedMessages();

}
