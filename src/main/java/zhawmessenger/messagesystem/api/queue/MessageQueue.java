package zhawmessenger.messagesystem.api.queue;

import ca.odell.glazedlists.EventList;
import zhawmessenger.messagesystem.api.scheduler.Schedulable;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.transport.SentMessage;

import java.util.Collection;
import java.util.List;

/**
 */
public interface MessageQueue extends Schedulable {

    SentMessage send(Message message);

    SentMessage send(Message message, boolean force);

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

    void remove(Message message);

    Collection<? extends QueuedMessage> getQueuedMessages();

    boolean contains(Message message);

}
