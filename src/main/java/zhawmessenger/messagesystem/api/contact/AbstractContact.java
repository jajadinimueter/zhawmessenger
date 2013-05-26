package zhawmessenger.messagesystem.api.contact;

/**
 */
public abstract class AbstractContact implements Contact {
    private String contact;

    @Override
    public void setValue(String contact) {
        this.contact = contact;
    }

    @Override
    public String getValue() {
        return this.contact;
    }
}
