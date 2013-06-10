package zhawmessenger.messagesystem.api.modules.email.persistance;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.persistance.Repository;

import java.util.Collection;

/**
 */
public interface EmailContactRepository
        extends Repository<EmailContact> {

    Collection<EmailContact> find(String name, boolean startsWith);

    Collection<EmailContact> find(String name);

}
