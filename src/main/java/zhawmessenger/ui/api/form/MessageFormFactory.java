package zhawmessenger.ui.api.form;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.CancelObservable;
import zhawmessenger.ui.api.SaveObservable;

import java.awt.*;

/**
 * Must be implemented by create forms of
 */
public interface MessageFormFactory<M extends Message> {

    SavableForm<M> createForm(Window owner, M message);

}
