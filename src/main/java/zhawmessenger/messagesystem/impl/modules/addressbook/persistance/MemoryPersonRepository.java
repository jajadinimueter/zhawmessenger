package zhawmessenger.messagesystem.impl.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.impl.modules.addressbook.PersonImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class MemoryPersonRepository implements PersonRepository {

    private Set<Person> people;

    public MemoryPersonRepository() {
        this(new HashSet<Person>());
    }

    public MemoryPersonRepository(Set<Person> people) {
        this.people = people;
    }

    @Override
    public Collection<Person> find(String name) {
        return find(name, false);
    }

    @Override
    public Collection<Person> find(String name, boolean startsWith) {
        final Set<Person> found = new HashSet<Person>();
        for (Person person : people) {
            if (startsWith) {
                if (person.getName().indexOf(name) == 0) {
                    found.add(person);
                }
            } else {
                if (person.getName().equals(name)) {
                    found.add(person);
                }
            }
        }
        return found;
    }

    @Override
    public void store(Person person) {
        if (!this.people.contains(person)) {
            this.people.add(person);
        }
    }

    @Override
    public void delete(Person person) {
        this.people.remove(person);
    }
}
