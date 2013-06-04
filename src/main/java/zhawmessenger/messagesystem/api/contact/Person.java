package zhawmessenger.messagesystem.api.contact;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

import java.util.List;

/**
 */
public interface Person {

    String getName();

    String getFirstName();

    List<EmailContact> getEmailContacts();

    List<MobilePhoneContact> getMobilePhoneContacts();

}
