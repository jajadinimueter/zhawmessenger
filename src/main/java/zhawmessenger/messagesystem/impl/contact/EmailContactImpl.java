package zhawmessenger.messagesystem.impl.contact;

import zhawmessenger.messagesystem.api.contact.AbstractContact;
import zhawmessenger.messagesystem.api.contact.ContactValidationException;
import zhawmessenger.messagesystem.api.contact.EmailContact;

/**
 */
public class EmailContactImpl extends AbstractContact
        implements EmailContact {

    @Override
    public void validate() {
        if (!this.isValid()) {
            throw new ContactValidationException();
        }
    }

    @Override
    public boolean isValid() {
        // this is a fair validation. everything else
        // will most likely be too restrict or too
        // lax
        return this.getValue().contains("@");
    }
}
