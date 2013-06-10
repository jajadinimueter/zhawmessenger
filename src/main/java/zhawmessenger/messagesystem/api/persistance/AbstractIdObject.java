package zhawmessenger.messagesystem.api.persistance;

/**
 */
public class AbstractIdObject implements IdObject {
    private Object id;

    public AbstractIdObject(Object id) {
        this.id = id;
    }

    @Override
    public Object getId() {
        return id;
    }
}
