package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;

/**
 * This is an abstract class rather than an interface
 * because we want to explicitely extend JPanel.
 *
 * @see DefaultSavableForm for usage
 */
public abstract class MessageForm<T extends Message> extends JPanel {
    public abstract T getMessage();

    public abstract T getSavedMessage();
}
