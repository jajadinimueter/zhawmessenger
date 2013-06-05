package zhawmessenger.messagesystem.api.modules.addressbook;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

import java.util.Collection;

/**
 */
public interface Person {

    String getName();

    void setName(String name);

    Collection<EmailContact> getEmailContacts();

    Collection<MobilePhoneContact> getMobilePhoneContacts();

    void addEmailContact(EmailContact contact);

    void addMobilePhoneContact(MobilePhoneContact contact);

    void removeEmailContacat(EmailContact contact);

    void removeMobilePhoneContact(MobilePhoneContact contact);

}
