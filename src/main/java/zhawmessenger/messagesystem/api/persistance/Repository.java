package zhawmessenger.messagesystem.api.persistance;

/**
 */
public interface Repository<T extends IdObject> {
    T get(Object id);

    void store(T item);

    void delete(T item);
}
