package zhawmessenger.messagesystem.impl.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.auth.Principal;
import zhawmessenger.messagesystem.impl.persistance.AbstractMemoryRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class MemoryPersonRepository extends AbstractMemoryRepository<Person>
        implements PersonRepository {

    public MemoryPersonRepository(Collection<Person> items) {
        super(items);
    }

    @Override
    public Collection<Person> find(String name) {
        return find(name, false);
    }

    @Override
    public Collection<Person> find(String name, boolean startsWith) {
        final Set<Person> found = new HashSet<Person>();
        for (Person person : items) {
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
    public Person findByUsername(String username) {
        for (Person p : items) {
            Principal pri = p.getPrincipal();
            if (pri != null) {
                if (pri.getUsername().equals(username)) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public void store(Person person) {
        if (!this.items.contains(person)) {
            this.items.add(person);
        }
    }

    @Override
    public void delete(Person person) {
        this.items.remove(person);
    }

}
