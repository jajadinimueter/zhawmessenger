package zhawmessenger.messagesystem.impl.modules.mobilephone.persistance;

import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.persistance.MobilePhoneContactRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractSearchableRepository;

import java.util.Collection;

/**
 */
public class MemoryMobilePhoneContactRepository
        extends AbstractSearchableRepository<MobilePhoneContact>
        implements MobilePhoneContactRepository {

    public MemoryMobilePhoneContactRepository(Collection<MobilePhoneContact> items) {
        super(items);
    }

    @Override
    protected boolean matches(MobilePhoneContact item, String value, boolean startWith) {
        if (startWith) {
            return item.getValue().startsWith(value);
        } else {
            return item.getValue().equals(value);
        }
    }
}
