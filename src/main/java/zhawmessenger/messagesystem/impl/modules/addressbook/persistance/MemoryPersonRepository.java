package zhawmessenger.messagesystem.impl.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.auth.Principal;
import zhawmessenger.messagesystem.impl.persistance.AbstractMemoryRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractSearchableRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class MemoryPersonRepository extends AbstractSearchableRepository<Person>
        implements PersonRepository {

    public MemoryPersonRepository(Collection<Person> items) {
        super(items);
    }

    @Override
    protected boolean matches(Person item, String value, boolean startWith) {
        if (startWith) {
            return item.getName().startsWith(value);
        } else {
            return item.getName().equals(value);
        }
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
}
