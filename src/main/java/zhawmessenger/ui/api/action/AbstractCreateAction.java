package zhawmessenger.ui.api.action;


import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;

import javax.swing.*;

/**
 * Must be implemented by submodules
 */
public abstract class AbstractCreateAction
        extends AbstractManipluateAction {

    public AbstractCreateAction(JFrame owner, String name,
                                Class<? extends Message> messageClass,
                                MessageFactory messageFactory) {
        super(owner, name, messageClass, messageFactory);
    }

}
