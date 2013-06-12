package zhawmessenger.messagesystem.api.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.persistance.Repository;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;

import java.util.Collection;

/**
 */
public interface PersonRepository
        extends Repository<Person>, SearchableRepository<Person> {
    Person findByUsername(String username);
}
