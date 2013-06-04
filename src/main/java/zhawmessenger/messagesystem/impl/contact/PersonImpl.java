package zhawmessenger.messagesystem.impl.contact;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.contact.Person;

import java.util.List;

/**
 */
public class PersonImpl implements Person {

    @Override
    public String getName() {
        return null;  // FIXME
    }

    @Override
    public String getFirstName() {
        return null;  // FIXME
    }

    @Override
    public List<EmailContact> getEmailContacts() {
        return null;  // FIXME
    }

    @Override
    public List<MobilePhoneContact> getMobilePhoneContacts() {
        return null;  // FIXME
    }
}
