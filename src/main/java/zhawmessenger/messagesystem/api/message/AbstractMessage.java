package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class AbstractMessage<R extends Contact> implements Message<R> {
    private String text;
    private long sendTime;

    private final List<R> receivers = new ArrayList<R>();

    @Override
    public long getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(long time) {
        this.sendTime = time;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void addReceiver(R receiver) {
        this.receivers.add(receiver);
    }

    @Override
    public void removeReceiver(R receiver) {
        this.receivers.remove(receiver);
    }

    @Override
    public List<R> getReceivers() {
        return this.receivers;
    }

    @Override
    public void validate() {
        if (!this.isValid()) {
            throw new MessageValidationException();
        }
    }
}
