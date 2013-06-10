package zhawmessenger.messagesystem.impl.modules.email.persistance;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractMemoryRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class MemoryEmailContactRepository
        extends AbstractMemoryRepository<EmailContact>
        implements EmailContactRepository {

    public MemoryEmailContactRepository(Collection<EmailContact> items) {
        super(items);
    }

    @Override
    public Collection<EmailContact> find(String name, boolean startsWith) {
        final Set<EmailContact> found = new HashSet<EmailContact>();
        for (EmailContact contact : items) {
            if (startsWith) {
                if (contact.getValue().indexOf(name) == 0) {
                    found.add(contact);
                }
            } else {
                if (contact.getValue().equals(name)) {
                    found.add(contact);
                }
            }
        }
        return found;
    }

    @Override
    public Collection<EmailContact> find(String name) {
        return find(name, false);
    }
}
