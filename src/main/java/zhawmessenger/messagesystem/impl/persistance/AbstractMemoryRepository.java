package zhawmessenger.messagesystem.impl.persistance;

import zhawmessenger.messagesystem.api.persistance.IdObject;
import zhawmessenger.messagesystem.api.persistance.Repository;

import java.util.Collection;

/**
 */
public abstract class AbstractMemoryRepository<T extends IdObject> implements Repository<T> {

    protected final Collection<T> items;

    public AbstractMemoryRepository(Collection<T> items) {
        this.items = items;
    }

    @Override
    public T get(Object id) {
        for (T i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void store(T item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    @Override
    public void delete(T item) {
        items.remove(item);
    }
}
