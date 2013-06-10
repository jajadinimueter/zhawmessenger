package zhawmessenger.messagesystem.api.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.persistance.AbstractIdObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 */
public abstract class AbstractMessage<R extends Contact>
        extends AbstractIdObject implements Message<R> {

    private String text;
    private long sendTime;

    private final List<R> receivers = new ArrayList<R>();

    public AbstractMessage(Object id) {
        super(id);
    }

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
