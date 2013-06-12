package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.persistance.AbstractIdObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

/**
 */
public abstract class AbstractMessage<R extends Contact>
        extends AbstractIdObject implements Message<R> {

    private String text;
    private Date sendTime;

    private final List<DisplayableContactProvider> contactProviders
            = new ArrayList<DisplayableContactProvider>();

    protected abstract Class<R> getContactClass();

    public AbstractMessage(Object id) {
        super(id);
    }

    @Override
    public Date getSendDate() {
        return this.sendTime;
    }

    public void setSendDate(Date time) {
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
    public void addContactProvider(DisplayableContactProvider provider) {
        contactProviders.add(provider);
    }

    @Override
    public List<DisplayableContactProvider> getContactProviders() {
        return contactProviders;
    }

    @Override
    public void clearContactProviders() {
        this.contactProviders.clear();
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
