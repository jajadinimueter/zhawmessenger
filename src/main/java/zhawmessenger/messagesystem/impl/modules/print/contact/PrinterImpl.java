package zhawmessenger.messagesystem.impl.modules.print.contact;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.contact.AbstractContact;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.modules.print.contact.Printer;

/**
 */
public class PrinterImpl
        extends AbstractContact
        implements Printer {

    public PrinterImpl(Object id, String value) {
        super(id, value);
    }

    @Override
    protected Class<? extends Contact> getContactClass() {
        return Printer.class;
    }

    @Override
    public boolean isValid() {
        throw new NotImplementedException();
    }
}
