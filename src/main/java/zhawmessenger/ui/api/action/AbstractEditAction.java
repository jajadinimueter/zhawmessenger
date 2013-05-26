package zhawmessenger.ui.api.action;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;

import javax.swing.*;

/**
 */
public abstract class AbstractEditAction
        extends AbstractManipluateAction {

    @SuppressWarnings("UnusedDeclaration")
    public AbstractEditAction(JFrame owner, String name,
                              Class<? extends Message> messageClass,
                              MessageFactory messageFactory) {
        super(owner, name, messageClass, messageFactory);
    }

}
