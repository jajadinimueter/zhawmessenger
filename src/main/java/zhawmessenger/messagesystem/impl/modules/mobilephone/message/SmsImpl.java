package zhawmessenger.messagesystem.impl.modules.mobilephone.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import zhawmessenger.messagesystem.api.message.AbstractMessage;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class SmsImpl extends AbstractMessage<MobilePhoneContact> implements Sms {

    @Override
    public boolean isValid() {
        // FIXME
        return true;
    }

    @Override
    public List<String> getMessageParts() {
        int start = 0, end = 160;
        String text = this.getText();
        ArrayList<String> parts = new ArrayList<String>();
        if (text != null) {
            while (end < text.length()) {
                parts.add(text.substring(start,end));
                start = end + 1;
                end = end + 160;
            }
            // the rest
            if (end >= text.length()) {
                parts.add(text.substring(start, text.length()));
            }
        }
        return parts;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SmsImpl)) {
            return false;
        }
        //noinspection SillyAssignment
        SmsImpl other = (SmsImpl) obj;

        return new EqualsBuilder()
                .append(this.getId(), other.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 31)
                .append(this.getId())
                .toHashCode();
    }
}
