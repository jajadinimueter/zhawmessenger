package zhawmessenger.messagesystem.api.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
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

    private final List<ContactProvider> contactProviders = new ArrayList<ContactProvider>();
    private final List<R> receivers = new ArrayList<R>();

    protected abstract Class<R> getContactClass();

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
    public void addContactProvider(ContactProvider provider) {
        contactProviders.add(provider);
    }

    @Override
    public List<ContactProvider> getContactProviders() {
        return contactProviders;
    }

    @Override
    public List<R> getReceivers() {
        // collect receivers
        List<R> receivers = new ArrayList<R>();
        for (ContactProvider provider : contactProviders) {
            receivers.addAll(provider.getContacts(getContactClass()));
        }
        return receivers;
    }

    @Override
    public void validate() {
        if (!this.isValid()) {
            throw new MessageValidationException();
        }
    }
}
