package zhawmessenger.messagesystem.impl.modules.addressbook;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.persistance.AbstractIdObject;

import java.util.*;

/**
 */
public class GroupImpl extends AbstractIdObject implements Group {

    private final Set<Person> people;
    private String name;

    public GroupImpl(Object id, String name) {
        this(id, new HashSet<Person>(), name);
    }

    public GroupImpl(Object id, Set<Person> people, String name) {
        super(id);
        this.people = people;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<Person> getPeople() {
        return this.people;
    }

    @Override
    public void addPerson(Person person) {
        this.getPeople().add(person);
    }

    @Override
    public void removePerson(Person person) {
        this.getPeople().remove(person);
    }

    @Override
    public Object getId() {
        return null;  // FIXME
    }

    /**
     * Just collect all contacts of the requested class
     * from our peaople.
     *
     * @param contactClass Required contact class
     * @param <T> The contact type
     *
     * @return collection of contacts with class "contactClass"
     */
    @Override
    public <T extends Contact> Collection<T> getContacts(Class<T> contactClass) {
        List<T> contacts = new ArrayList<T>();
        for (Person p : people) {
            contacts.addAll(p.getContacts(contactClass));
        }
        return contacts;
    }

    @Override
    public String getDisplay() {
        return getName();
    }
}
