package zhawmessenger.ui.api.action;

import zhawmessenger.ui.api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 */
public abstract class AbstractManipluateAction
        extends AbstractAction
        implements SaveObservable, CancelObservable {

    private JFrame owner;
    private MessageFormFactory messageForm;

    public AbstractManipluateAction(JFrame owner, String name) {
        super(name);
        this.owner = owner;
        //noinspection unchecked
        this.messageForm = this.getForm();
    }

    @Override
    public void addSaveListener(SaveListener listener) {
        this.messageForm.addSaveListener(listener);
    }

    @Override
    public void addCancelListener(CancelListener listener) {
        this.messageForm.addCancelListener(listener);
    }

    /**
     * To be implemented by modules. This must return
     * a form interface with a display
     */
    protected abstract MessageFormFactory getForm();

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog window = new JDialog(this.owner, "Create", true);
        window.setLocationRelativeTo(this.owner);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(this.messageForm.createForm());
        window.setSize(400,800);
        window.setVisible(true);
    }
}
