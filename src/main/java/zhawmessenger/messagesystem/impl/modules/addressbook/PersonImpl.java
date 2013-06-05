package zhawmessenger.messagesystem.impl.modules.addressbook;

import com.sun.javaws.exceptions.InvalidArgumentException;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class PersonImpl implements Person {

    private String name;

    private final Set<EmailContact> emailContacts;
    private final Set<MobilePhoneContact> mobilePhoneContacts;

    public PersonImpl(String name) {
        this(name, new HashSet<MobilePhoneContact>(),
                new HashSet<EmailContact>());
    }

    public PersonImpl(String name,
                      Set<MobilePhoneContact> mobilePhoneContacts,
                      Set<EmailContact> emailContacts) {
        this.mobilePhoneContacts = mobilePhoneContacts;
        this.emailContacts = emailContacts;
        this.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || "".equals(name)) {
            throw new RuntimeException();
        }
        this.name = name;
    }

    @Override
    public Collection<EmailContact> getEmailContacts() {
        return this.emailContacts;
    }

    @Override
    public Collection<MobilePhoneContact> getMobilePhoneContacts() {
        return this.mobilePhoneContacts;
    }

    @Override
    public void addEmailContact(EmailContact contact) {
        this.getEmailContacts().add(contact);
    }

    @Override
    public void addMobilePhoneContact(MobilePhoneContact contact) {
        this.getMobilePhoneContacts().add(contact);
    }

    @Override
    public void removeEmailContacat(EmailContact contact) {
        this.getEmailContacts().remove(contact);
    }

    @Override
    public void removeMobilePhoneContact(MobilePhoneContact contact) {
        this.getMobilePhoneContacts().remove(contact);
    }
}
