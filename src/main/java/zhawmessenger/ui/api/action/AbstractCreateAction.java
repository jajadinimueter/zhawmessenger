package zhawmessenger.ui.api.action;


import javax.swing.*;

/**
 * Must be implemented by submodules
 */
public abstract class AbstractCreateAction
        extends AbstractManipluateAction {

    public AbstractCreateAction(JFrame owner, String name) {
        super(owner, name);
    }

}
