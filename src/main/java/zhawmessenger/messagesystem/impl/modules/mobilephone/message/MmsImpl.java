package zhawmessenger.messagesystem.impl.modules.mobilephone.message;

import zhawmessenger.messagesystem.api.message.AbstractMessage;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;

/**
 */
public class MmsImpl
        extends AbstractMessage<MobilePhoneContact>
        implements Mms {

    public MmsImpl(Object id) {
        super(id);
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];  // FIXME
    }

    @Override
    public boolean isValid() {
        return false;  // FIXME
    }
}
