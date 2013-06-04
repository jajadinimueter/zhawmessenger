package zhawmessenger.ui.api.action;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
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
    private MessageFormFactory messageFormFactory;
    private MessageFactory messageFactory;
    private Class<? extends Message> messageClass;

    public AbstractManipluateAction(JFrame owner,
                                    String name,
                                    Class<? extends Message> messageClass,
                                    MessageFactory messageFactory) {
        super(name);
        this.owner = owner;
        this.messageClass = messageClass;
        this.messageFactory = messageFactory;
        //noinspection unchecked
        this.messageFormFactory = this.getForm();
    }

    @Override
    public void addSaveListener(SaveListener listener) {
        this.messageFormFactory.addSaveListener(listener);
    }

    @Override
    public void addCancelListener(CancelListener listener) {
        this.messageFormFactory.addCancelListener(listener);
    }

    /**
     * To be implemented by modules. This must return
     * a form interface with a display
     */
    protected abstract MessageFormFactory getForm();

    @Override
    public void actionPerformed(ActionEvent e) {
        final JDialog window = new JDialog(this.owner, "Create", true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(this.messageFormFactory.createForm(
                this.messageFactory.createMessage(this.messageClass)));
        window.setSize(700,700);

        this.addCancelListener(new CancelListener() {
            @Override
            public void canceled(Message message) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }
}
