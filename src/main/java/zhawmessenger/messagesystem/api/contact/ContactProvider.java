package zhawmessenger.messagesystem.api.contact;

import zhawmessenger.messagesystem.api.persistance.IdObject;

import java.util.Collection;
import java.util.List;

/**
 */
public interface ContactProvider extends IdObject {
    <T extends Contact> Collection<T> getContacts(Class<T> contactClass);
}
