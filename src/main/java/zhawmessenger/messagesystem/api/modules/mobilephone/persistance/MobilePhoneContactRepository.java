package zhawmessenger.messagesystem.api.modules.mobilephone.persistance;

import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.persistance.Repository;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;

/**
 */
public interface MobilePhoneContactRepository
        extends Repository<MobilePhoneContact>, SearchableRepository<MobilePhoneContact>{
}
