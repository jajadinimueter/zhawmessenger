package zhawmessenger.messagesystem.api.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;

import java.util.Collection;

/**
 */
public interface PersonRepository {

    Collection<Person> find(String name);

    Collection<Person> find(String name, boolean startsWith);

    Person findByUsername(String username);

    void store(Person person);

    void delete(Person person);
}
