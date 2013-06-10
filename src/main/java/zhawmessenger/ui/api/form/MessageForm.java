package zhawmessenger.ui.api.form;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;
import java.awt.*;

/**
 * This is an abstract class rather than an interface
 * because we want to explicitely extend JPanel.
 *
 * @see DefaultSavableForm for usage
 */
public abstract class MessageForm<T extends Message> extends JPanel {
    protected final T message;
    protected final Window owner;

    protected MessageForm(Window owner, T message) {
        this.message = message;
        this.owner = owner;
    }

    public abstract T getMessage();

    public abstract T getSavedMessage();
}
