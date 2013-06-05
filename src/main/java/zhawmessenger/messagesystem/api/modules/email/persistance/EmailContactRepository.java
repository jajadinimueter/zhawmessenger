package zhawmessenger.messagesystem.api.modules.email.persistance;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;

import java.util.Collection;

/**
 */
public interface EmailContactRepository {

    Collection<EmailContact> find(String name, boolean startsWith);

    Collection<EmailContact> find(String name);

    void store(EmailContact contact);

    void remove(EmailContact contact);

}
