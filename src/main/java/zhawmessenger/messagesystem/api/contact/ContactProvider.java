package zhawmessenger.messagesystem.api.contact;

import java.util.Collection;
import java.util.List;

/**
 */
public interface ContactProvider {
    <T extends Contact> Collection<T> getContacts(Class<T> contactClass);
}
