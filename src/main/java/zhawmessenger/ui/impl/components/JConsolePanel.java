package zhawmessenger.ui.impl.components;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.util.SentMessageLogger;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * A logger panel which implements {@link zhawmessenger.messagesystem.api.util.SentMessageLogger} so
 * it can be used directly in transports to log sent messages.
 */
public class JConsolePanel extends JPanel implements SentMessageLogger {
    private final JTextAreaWithScroll logTextArea;

    public JConsolePanel() {
        this.setLayout(new BorderLayout());
        this.logTextArea = new JTextAreaWithScroll(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.logTextArea.setEditable(false);
        this.add(this.logTextArea.getScrollPane(),
                BorderLayout.CENTER);
    }

    @Override
    public void log(Message message, Date sendAt) {
        this.logTextArea.insert(String.format("Message sent: %s\n", message), 0);
        this.logTextArea.setCaretPosition(0);
    }

    @Override
    public void log(SentMessage message) {
        this.logTextArea.insert(String.format("Message sent: %s\n", message.getMessage()), 0);
        this.logTextArea.setCaretPosition(0);
    }
}
