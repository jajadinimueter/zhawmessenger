package zhawmessenger.messagesystem.impl.modules.addressbook.persistance;

import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.GroupRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractMemoryRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractSearchableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
public class MemoryGroupRepository
        extends AbstractSearchableRepository<Group>
        implements GroupRepository {

    public MemoryGroupRepository(Collection<Group> items) {
        super(items);
    }

    @Override
    protected boolean matches(Group item, String value, boolean startWith) {
        if ( startWith ) {
            return item.getName().startsWith(value);
        } else {
            return item.getName().equals(value);
        }
    }
}
