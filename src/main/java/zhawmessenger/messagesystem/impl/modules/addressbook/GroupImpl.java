package zhawmessenger.messagesystem.impl.modules.addressbook;

import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class GroupImpl implements Group {

    private final Set<Person> people;
    private String name;

    public GroupImpl(String name) {
        this(new HashSet<Person>(), name);
    }

    public GroupImpl(Set<Person> people, String name) {
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
}
