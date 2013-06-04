package zhawmessenger.messagesystem.api.queue;

import ca.odell.glazedlists.EventList;
import zhawmessenger.messagesystem.api.Schedulable;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.transport.SentMessage;

/**
 */
public interface MessageQueue extends Schedulable {

    void send(QueuedMessage message);

    SentMessage send(QueuedMessage message, boolean force);

    void sendAll();

    void sendAll(boolean force);

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

    boolean contains(QueuedMessage message);

    boolean contains(Message message);

}
