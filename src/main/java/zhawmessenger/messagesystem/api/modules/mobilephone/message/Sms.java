package zhawmessenger.messagesystem.api.modules.mobilephone.message;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

import java.util.List;

/**
 */
public interface Sms extends ShortMessage {

    /**
     * One SMS is limited to 160 chars. Since the system allows
     * messages over 160 chars we have to split the message into
     * multiple parts before sending.
     *
     * Whoever sends us has to take care of that.
     *
     * @return A list of message parts.
     */
    List<String> getMessageParts();

}
