package zhawmessenger.messagesystem.api.modules.addressbook;

import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.persistance.IdObject;

import java.util.Collection;

/**
 */
public interface Group extends IdObject, DisplayableContactProvider {

    String getName();

    void setName(String name);

    Collection<Person> getPeople();

    void addPerson(Person person);

    void removePerson(Person person);
}
