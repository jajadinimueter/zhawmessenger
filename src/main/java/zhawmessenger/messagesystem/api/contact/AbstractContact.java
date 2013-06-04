package zhawmessenger.messagesystem.api.contact;

/**
 */
public abstract class AbstractContact implements Contact {
    private String contact;

    public AbstractContact(){}

    public AbstractContact(String value) {
        this.setValue(value);
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
}
