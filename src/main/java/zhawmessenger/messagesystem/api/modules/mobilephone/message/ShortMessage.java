package zhawmessenger.messagesystem.api.modules.mobilephone.message;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.remind.Remindable;

/**
 */
public interface ShortMessage extends Message<MobilePhoneContact>,
        Remindable<MobilePhoneContact>{
    byte[] getBytes();
}
