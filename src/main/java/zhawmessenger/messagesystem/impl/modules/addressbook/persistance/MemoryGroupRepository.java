package zhawmessenger.messagesystem.impl.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.GroupRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractMemoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
public class MemoryGroupRepository extends AbstractMemoryRepository<Group> implements GroupRepository {
    public MemoryGroupRepository(Collection<Group> items) {
        super(items);
    }

    @Override
    public Collection<Group> find(String name) {
        return find(name, true);
    }

    @Override
    public Collection<Group> find(String name, boolean startsWith) {
        List<Group> found = new ArrayList<Group>();
        for (Group group : items) {
            if (startsWith) {
                if (group.getName().startsWith(name)) {
                    found.add(group);
                }
            } else {
                if (group.getName().equals(name)) {
                    found.add(group);
                }
            }
        }
        return found;
    }
}
