package zhawmessenger.messagesystem.api.queue;

/**
 */
public interface MessageQueueListener {

    void removed(QueuedMessage message);

}
