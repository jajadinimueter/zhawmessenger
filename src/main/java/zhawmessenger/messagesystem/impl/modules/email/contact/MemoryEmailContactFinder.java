package zhawmessenger.messagesystem.impl.modules.email.contact;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.GroupRepository;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailRepository;
import zhawmessenger.messagesystem.api.util.Finder;

import java.util.*;

/**
 */
public class MemoryEmailContactFinder implements Finder<String, DisplayableContactProvider> {

    private EmailContactRepository repository;
    private GroupRepository groupRepository;
    private PersonRepository personRepository;

    public MemoryEmailContactFinder(EmailContactRepository repository,
                                    GroupRepository groupRepository,
                                    PersonRepository personRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Set<DisplayableContactProvider> find(String... findArgs) {
        final Set<DisplayableContactProvider> foundContacts =
                new HashSet<DisplayableContactProvider>();

        for (String searchString : findArgs) {
            foundContacts.addAll(this.repository.find(searchString, true));
            foundContacts.addAll(this.groupRepository.find(searchString, true));
            foundContacts.addAll(this.personRepository.find(searchString, true));
        }

        return foundContacts;
    }
}
