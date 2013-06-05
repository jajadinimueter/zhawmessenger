package zhawmessenger.messagesystem.impl.modules.email.persistance;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class MemoryEmailContactRepository implements EmailContactRepository {

    private Set<EmailContact> contacts;

    public MemoryEmailContactRepository(Set<EmailContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Collection<EmailContact> find(String name, boolean startsWith) {
        final Set<EmailContact> found = new HashSet<EmailContact>();
        for (EmailContact contact : contacts) {
            if (startsWith) {
                if (contact.getValue().indexOf(name) == 0) {
                    found.add(contact);
                }
            } else {
                if (contact.getValue().equals(name)) {
                    found.add(contact);
                }
            }
        }
        return found;
    }

    @Override
    public Collection<EmailContact> find(String name) {
        return find(name, false);
    }

    @Override
    public void store(EmailContact contact) {
        this.contacts.add(contact);
    }

    @Override
    public void remove(EmailContact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
        }
    }
}
