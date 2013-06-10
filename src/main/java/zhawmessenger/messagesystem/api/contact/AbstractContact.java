package zhawmessenger.messagesystem.api.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 */
public abstract class AbstractContact implements Contact {
    private String contact;
    private Object id;

    public AbstractContact(){}

    public AbstractContact(Object id, String value) {
        this.id = id;
        this.setValue(value);
    }

    /**
     * This has to be implemented because java generics
     * are not capable to do the following:
     *
     *      <T> T foo(Class<T> t) {
     *          if ( T.isAssignableFrom(...)
     *      }
     *
     * The method we need this is: {@link #getContacts}
     *
     * @return a contact class
     */
    protected abstract Class<? extends Contact> getContactClass();

    @Override
    public String getDisplay() {
        return getValue();
    }

    @Override
    public <T extends Contact> Collection<T> getContacts(Class<T> contactClass) {
        if (getContactClass().isAssignableFrom(contactClass)) {
            //noinspection unchecked
            return Arrays.asList(contactClass.cast(this));
        }
        return new ArrayList<T>();
    }

    @Override
    public void setValue(String contact) {
        this.contact = contact;
    }

    @Override
    public String getValue() {
        return this.contact;
    }

    @Override
    public void validate() {
        if (!this.isValid()) {
            throw new ContactValidationException();
        }
    }

    @Override
    public Object getId() {
        return this.id;
    }
}
