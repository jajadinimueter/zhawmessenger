package zhawmessenger.messagesystem.api.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;

import java.util.Collection;

/**
 */
public interface SearchableRepository<T> {
    Collection<T> find(String name);

    Collection<T> find(String name, boolean startsWith);
}
