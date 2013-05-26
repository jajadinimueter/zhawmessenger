package zhawmessenger.messagesystem.api.contact;

/**
 */
public interface Contact {
    void setValue(String contact);

    String getValue();

    void validate();

    boolean isValid();
}
