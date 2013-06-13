package zhawmessenger.messagesystem.impl.modules.addressbook;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.auth.Principal;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.messagesystem.api.persistance.AbstractIdObject;
import zhawmessenger.messagesystem.impl.modules.auth.MemoryPrincipal;

import java.util.*;

/**
 */
public class PersonImpl
        extends AbstractIdObject implements Person {

    private String name;
    private Principal principal;

    private final Set<EmailContact> emailContacts;
    private final Set<MobilePhoneContact> mobilePhoneContacts;

    public PersonImpl(Object id, final String name) {
        this(id, name, name);
    }

    public PersonImpl(Object id,
                      final String name,
                      final String username) {
        this(id, name, username, new HashSet<MobilePhoneContact>(),
                new HashSet<EmailContact>());
    }

    public PersonImpl(Object id,
                      final String name,
                      final String username,
                      final Set<MobilePhoneContact> mobilePhoneContacts,
                      final Set<EmailContact> emailContacts) {
        super(id);
        this.principal = new MemoryPrincipal(username);
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

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    /**
     * We return either {@link #getEmailContacts()} or {@link #getMobilePhoneContacts()}
     * depending on the requested type.
     *
     * @param contactClass the requested class
     * @param <T> the type of requested class
     *
     * @return list of contacts if any are defined
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Contact> Collection<T> getContacts(Class<T> contactClass) {
        if (EmailContact.class.isAssignableFrom(contactClass)) {
            return (Collection<T>) getEmailContacts();
        } else if (MobilePhoneContact.class.isAssignableFrom(contactClass)) {
            return (Collection<T>) getMobilePhoneContacts();
        }
        return new ArrayList<T>();
    }

    @Override
    public String getDisplay() {
        return getName();
    }
}
