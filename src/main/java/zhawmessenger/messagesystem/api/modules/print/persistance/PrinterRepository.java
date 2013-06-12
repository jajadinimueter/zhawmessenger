package zhawmessenger.messagesystem.api.modules.print.persistance;

import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.persistance.Repository;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;

import java.util.List;

/**
 */
public interface PrinterRepository
        extends Repository<Printer>, SearchableRepository<Printer> {
}
