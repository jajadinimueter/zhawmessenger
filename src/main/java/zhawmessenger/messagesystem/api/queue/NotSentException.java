package zhawmessenger.messagesystem.api.queue;

/**
 */
public class NotSentException extends RuntimeException {
    public NotSentException(QueuedMessage message) {
        super(message.toString());
    }
}
