package zhawmessenger.messagesystem.impl.modules.print.message;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.message.AbstractMessage;
import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.modules.print.message.PrintJob;

/**
 */
public class PrintJobnImpl
        extends AbstractMessage<Printer>
        implements PrintJob {

    @Override
    protected Class<Printer> getContactClass() {
        return Printer.class;
    }

    public PrintJobnImpl(Object id) {
        super(id);
    }

    @Override
    public boolean isValid() {
        throw new NotImplementedException();
    }

}
