package zhawmessenger.messagesystem.api.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.persistance.Repository;

import java.util.Collection;

/**
 */
public interface GroupRepository extends Repository<Group> {
    Collection<Group> find(String name);

    Collection<Group> find(String name, boolean startsWith);
}
