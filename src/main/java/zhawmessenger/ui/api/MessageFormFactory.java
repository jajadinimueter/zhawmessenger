package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;
import java.awt.*;

/**
 * Must be implemented by create forms of
 */
public interface MessageFormFactory<M extends Message>
        extends SaveObservable, CancelObservable {

    JPanel createForm(Window owner, M message);

}
