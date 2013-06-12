package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.persistance.IdObject;

import java.util.Date;
import java.util.List;

/**
 */
public interface Message<R extends Contact> extends IdObject {

    /**
     * Returns an ID with uniqually identifies
     * this message
     *
     * @return an ID
     */
    Date getSendDate();

    void setSendDate(Date date);

    String getText();

    void setText(String text);

    void validate();

    boolean isValid();

    void clearContactProviders();

    void addContactProvider(DisplayableContactProvider provider);

    List<DisplayableContactProvider> getContactProviders();

    List<R> getReceivers();
}
