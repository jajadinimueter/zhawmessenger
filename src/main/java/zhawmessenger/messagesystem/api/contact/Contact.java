package zhawmessenger.messagesystem.api.contact;

import zhawmessenger.messagesystem.api.persistance.IdObject;

/**
 */
public interface Contact extends IdObject, DisplayableContactProvider {
    void setValue(String contact);

    String getValue();

    void validate() throws ContactValidationException;

    boolean isValid();
}
