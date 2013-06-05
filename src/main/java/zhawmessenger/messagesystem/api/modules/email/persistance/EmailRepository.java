package zhawmessenger.messagesystem.api.modules.email.persistance;

import zhawmessenger.messagesystem.api.modules.email.message.Email;

/**
 */
public interface EmailRepository {
    Email find(String address);

    Email find(String address, boolean startsWith);

    void store(Email email);

    void delete(Email email);
}
