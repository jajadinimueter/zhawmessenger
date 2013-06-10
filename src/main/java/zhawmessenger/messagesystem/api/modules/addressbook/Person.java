package zhawmessenger.messagesystem.api.modules.addressbook;

import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.auth.Principal;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.persistance.IdObject;

import java.util.Collection;

/**
 */
public interface Person extends IdObject, DisplayableContactProvider {

    String getName();

    void setName(String name);

    Collection<EmailContact> getEmailContacts();

    Collection<MobilePhoneContact> getMobilePhoneContacts();

    void addEmailContact(EmailContact contact);

    void addMobilePhoneContact(MobilePhoneContact contact);

    void removeEmailContacat(EmailContact contact);

    void removeMobilePhoneContact(MobilePhoneContact contact);

    Principal getPrincipal();

}
