package zhawmessenger.messagesystem.impl.contact;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.util.Finder;

import java.util.*;

/**
 */
public class MemoryEmailContactFinder implements Finder<String, EmailContact> {

    private Collection<EmailContact> contacts;

    public MemoryEmailContactFinder(Collection<EmailContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Set<EmailContact> find(String... findArgs) {
        final Set<EmailContact> foundContacts =
                new HashSet<EmailContact>();
        if (this.contacts != null) {
            for (EmailContact contact : this.contacts) {
                for (String arg : findArgs) {
                    if (contact.getValue().contains(arg)) {
                        foundContacts.add(contact);
                    }
                }
            }
        }
        return foundContacts;
    }
}
