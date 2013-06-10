package zhawmessenger.ui.impl.modules.print;

import zhawmessenger.messagesystem.api.modules.print.message.PrintJob;
import zhawmessenger.ui.api.form.MessageForm;

import java.awt.*;

/**
 */
public class PrintForm
        extends MessageForm<PrintJob> {

    protected PrintForm(Window owner, PrintJob message) {
        super(owner, message);
    }

    @Override
    public PrintJob getMessage() {
        return null;  // FIXME
    }

    @Override
    public PrintJob getSavedMessage() {
        return null;  // FIXME
    }
}
