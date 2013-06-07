package zhawmessenger.messagesystem.impl.queue;

import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.persistance.QueueRepository;

import java.util.Collection;
import java.util.List;

/**
 */
public class MemoryQueueRepository implements QueueRepository {
    private List<QueuedMessage> messages;

    public MemoryQueueRepository(List<QueuedMessage> messages) {
        this.messages = messages;
    }

    @Override
    public Collection<QueuedMessage> find() {
        return this.messages;
    }

    @Override
    public void markSending(QueuedMessage message) {
        // FIXME
    }

    @Override
    public void markSent(QueuedMessage message) {
        // FIXME
    }

    @Override
    public void store(QueuedMessage message) {
        if (!this.messages.contains(message)) {
            this.messages.add(message);
        }
    }
}
