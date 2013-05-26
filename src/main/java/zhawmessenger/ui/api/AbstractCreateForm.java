package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.Message;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class AbstractCreateForm<M extends Message>
        implements CreateFormFactory<M> {

    protected List<SaveListener> saveListeners;
    protected List<CancelListener> cancelListeners;

    public AbstractCreateForm() {
        saveListeners = new ArrayList<SaveListener>();
        cancelListeners = new ArrayList<CancelListener>();
    }

    @Override
    public void addSaveListener(SaveListener listener) {
        this.saveListeners.add(listener);
    }

    @Override
    public void addCancelListener(CancelListener listener) {
        this.cancelListeners.add(listener);
    }

}
