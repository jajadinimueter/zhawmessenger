package zhawmessenger.messagesystem.api.modules.mobilephone.message;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

/**
 */
public interface ShortMessage extends Message<MobilePhoneContact> {
    byte[] getBytes();
}
