package zhawmessenger.messagesystem.impl.contact;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailRepository;
import zhawmessenger.messagesystem.api.util.Finder;

import java.util.*;

/**
 */
public class MemoryEmailContactFinder implements Finder<String, DisplayableContactProvider> {

    private EmailContactRepository repository;

    public MemoryEmailContactFinder(EmailContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<DisplayableContactProvider> find(String... findArgs) {
        final Set<DisplayableContactProvider> foundContacts =
                new HashSet<DisplayableContactProvider>();
        for (String searchString : findArgs) {
            foundContacts.addAll(this.repository.find(searchString, true));
        }
        return foundContacts;
    }
}
