package zhawmessenger.messagesystem.impl.modules.auth;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.auth.Authenticator;
import zhawmessenger.messagesystem.api.modules.auth.Credentials;
import zhawmessenger.messagesystem.api.modules.auth.Principal;

import java.util.List;

/**
 */
public class MemoryPersonAuthenticatorImpl implements Authenticator {

    private PersonRepository personRepository;

    public MemoryPersonAuthenticatorImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Principal authenticate(Credentials credentials) {
        Person person = personRepository.findByUsername(credentials.getUsername());
        return person.getPrincipal();
    }
}
