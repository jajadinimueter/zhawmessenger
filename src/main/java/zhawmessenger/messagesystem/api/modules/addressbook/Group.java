package zhawmessenger.messagesystem.api.modules.addressbook;

import java.util.Collection;

/**
 */
public interface Group {

    String getName();

    void setName(String name);

    Collection<Person> getPeople();

    void addPerson(Person person);

    void removePerson(Person person);
}
