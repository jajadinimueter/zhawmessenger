package zhawmessenger.messagesystem.impl.modules.print.persistance;

import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.modules.print.persistance.PrinterRepository;
import zhawmessenger.messagesystem.impl.persistance.AbstractSearchableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
public class MemoryPrinterRepository
        extends AbstractSearchableRepository<Printer>
        implements PrinterRepository {

    public MemoryPrinterRepository(Collection<Printer> items) {
        super(items);
    }

    @Override
    protected boolean matches(Printer item, String value, boolean startWith) {
        if (startWith) {
            return item.getValue().startsWith(value);
        } else {
            return item.getValue().equals(value);
        }
    }
}
