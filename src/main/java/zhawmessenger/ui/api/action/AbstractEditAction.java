package zhawmessenger.ui.api.action;

import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.ui.api.EditFormFactory;

import javax.swing.*;

/**
 */
public abstract class AbstractEditAction
        extends AbstractManipluateAction {

    @SuppressWarnings("UnusedDeclaration")
    public AbstractEditAction(JFrame owner, String name) {
        super(owner, name);
    }

}
