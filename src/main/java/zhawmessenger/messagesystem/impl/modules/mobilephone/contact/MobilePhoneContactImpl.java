package zhawmessenger.messagesystem.impl.modules.mobilephone.contact;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import zhawmessenger.messagesystem.api.contact.AbstractContact;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

/**
 */
public class MobilePhoneContactImpl extends AbstractContact implements MobilePhoneContact {
    private static final String PHONE_NUMBER_REGEX = "";

    public MobilePhoneContactImpl(Object id, String value) {
        super(id, value);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean isValid() {
        if (this.getValue() == null) {
            return false;
        }
        return this.getValue().matches(PHONE_NUMBER_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MobilePhoneContactImpl)) {
            return false;
        }
        //noinspection SillyAssignment
        MobilePhoneContactImpl other = (MobilePhoneContactImpl) obj;

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

    @Override
    protected Class<? extends Contact> getContactClass() {
        return MobilePhoneContact.class;
    }
}
