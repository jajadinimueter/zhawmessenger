package zhawmessenger.messagesystem.impl.persistance;

import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.persistance.IdObject;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
public abstract class AbstractSearchableRepository<T extends IdObject>
    extends AbstractMemoryRepository<T> implements SearchableRepository<T> {

    public AbstractSearchableRepository(Collection<T> items) {
        super(items);
    }

    public List<T> find(String value) {
        return find(value, false);
    }

    protected abstract boolean matches(T item, String value, boolean startWith);

    public List<T> find(String value, boolean startsWith) {
        List<T> found = new ArrayList<T>();
        for (T p : items) {
            if (this.matches(p, value, startsWith)) {
                found.add(p);
            }
        }
        return found;
    }
}
