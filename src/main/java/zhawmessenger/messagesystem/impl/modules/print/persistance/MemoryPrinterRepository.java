package zhawmessenger.messagesystem.impl.modules.print.persistance;

import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.modules.print.persistance.PrinterRepository;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MemoryPrinterRepository implements PrinterRepository {

    private final List<Printer> printers;

    public MemoryPrinterRepository(List<Printer> printers) {
        this.printers = printers;
    }

    @Override
    public List<Printer> find(String value) {
        return find(value, false);
    }

    @Override
    public List<Printer> find(String value, boolean startsWith) {
        List<Printer> found = new ArrayList<Printer>();
        for (Printer p : printers) {
            if (startsWith) {
                if (p.getValue().startsWith(value)) {
                    found.add(p);
                }
            } else if (p.getValue().equals(value)) {
                found.add(p);
            }
        }
        return found;
    }
}
