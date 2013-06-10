package zhawmessenger.messagesystem.impl.modules.email.contact;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import zhawmessenger.messagesystem.api.contact.AbstractContact;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.message.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 */
public class EmailContactImpl extends AbstractContact
        implements EmailContact {

    public EmailContactImpl(Object id, String value) {
        super(id, value);
    }

    @Override
    protected Class<? extends Contact> getContactClass() {
        return EmailContact.class;
    }

    @Override
    public boolean isValid() {
        // this is a fair validation. everything else
        // will most likely be too restrict or too lax
        return this.getValue().contains("@");
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EmailContactImpl)) {
            return false;
        }
        //noinspection SillyAssignment
        EmailContactImpl other = (EmailContactImpl) obj;

        return new EqualsBuilder()
                .append(this.getValue(), other.getValue())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 101)
                .append(this.getValue())
                .toHashCode();
    }
}
