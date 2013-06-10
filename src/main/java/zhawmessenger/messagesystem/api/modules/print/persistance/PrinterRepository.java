package zhawmessenger.messagesystem.api.modules.print.persistance;

import zhawmessenger.messagesystem.api.modules.print.contact.Printer;

import java.util.List;

/**
 */
public interface PrinterRepository {
    List<Printer> find(String value);

    List<Printer> find(String value, boolean startsWith);
}
