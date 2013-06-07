package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import java.awt.*;

/**
 * Must be implemented by create forms of
 */
public interface MessageFormFactory<M extends Message>
        extends SaveObservable, CancelObservable {

    SavableForm<M> createForm(Window owner, M message);

}
