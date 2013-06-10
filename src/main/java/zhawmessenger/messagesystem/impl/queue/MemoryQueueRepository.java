package zhawmessenger.messagesystem.impl.queue;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.persistance.QueueRepository;

import java.util.Collection;
import java.util.List;

/**
 */
public class MemoryQueueRepository implements QueueRepository {
    private List<QueuedMessageImpl> messages;

    /**
     * We accept just implementations because we are
     * actually the only one who know real queud messages
     *
     * @param messages the messages
     */
    public MemoryQueueRepository(List<QueuedMessageImpl> messages) {
        this.messages = messages;
    }

    @Override
    public Collection<? extends QueuedMessage> find() {
        return this.messages;
    }

    private QueuedMessageImpl getMessageImpl(QueuedMessage message) {
        int pos = messages.indexOf(message);
        return messages.get(pos);
    }

    @Override
    public void markSending(QueuedMessage message) {
        getMessageImpl(message)
                .setState(QueuedMessage.MessageState.SENDING);
    }

    @Override
    public void markSent(QueuedMessage message) {
        getMessageImpl(message)
                .setState(QueuedMessage.MessageState.SENT);
    }

    private QueuedMessageImpl getByMessage(Message message) {
        for (QueuedMessageImpl qmi : messages) {
            if (qmi.getMessage().equals(message)) {
                return qmi;
            }
        }
        return null;
    }

    @Override
    public QueuedMessage get(Message message) {
        return getByMessage(message);
    }

    @Override
    public QueuedMessage create(Message message) {
        QueuedMessageImpl qMsg = getByMessage(message);
        if (qMsg == null) {
            qMsg = new QueuedMessageImpl(message);
            this.messages.add(qMsg);
        }
        return qMsg;
    }

    @Override
    public void delete(Message message) {
        QueuedMessageImpl msg = getByMessage(message);
        if (msg != null) {
            messages.remove(msg);
        }
    }
}
