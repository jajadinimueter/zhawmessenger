package zhawmessenger.ui.impl.modules.print;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.print.message.PrintJob;
import zhawmessenger.messagesystem.impl.modules.print.message.PrintJobnImpl;
import zhawmessenger.ui.api.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 */
public class PrintMessagePlugin
        implements MessagePlugin<PrintJob> {
    @Override
    public boolean doesHandle(Class<PrintJob> messageClass) {
        return PrintJob.class.isAssignableFrom(messageClass);
    }

    @Override
    public Class<PrintJob> getMessageClass() {
        return PrintJob.class;
    }

    @Override
    public String getName() {
        return "Druckauftrag";
    }

    @Override
    public Icon getIcon() {
        return null;  // FIXME
    }

    @Override
    public ItemFormatter<PrintJob> getPreviewFormatter() {
        return null;  // FIXME
    }

    @Override
    public MessageFactory getMessageFactory() {
        return new MessageFactory() {
            @Override
            public Message createMessage() {
                return new PrintJobnImpl(UUID.randomUUID());
            }
        };
    }

    @Override
    public MessageWindowFactory getWindowFactory() {
        return new DefaultMessageWindowFactory();
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new AbstractFormFactory<PrintJob>() {
            @Override
            public SavableForm<PrintJob> createForm(Window owner, PrintJob message) {
                return new DefaultSavableForm<PrintJob>(
                        new PrintForm(owner, message));
            }
        };
    }
}
