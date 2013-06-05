package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.ui.impl.components.SuggesterValueExtractor;
import zhawmessenger.ui.impl.components.Suggestor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 */
public class EmailReceiverTextArea extends JTextArea {

    public EmailReceiverTextArea(Window owner, List<Finder<String, EmailContact>> finders,
                                 int rows, int columns){
        super(rows, columns);

        Suggestor<EmailContact> sugg = new Suggestor<EmailContact>(owner, this,
                new SuggesterValueExtractor<EmailContact>() {
                    @Override
                    public String extract(EmailContact item) {
                        return item.getValue();
                    }
                }, finders);

        this.getDocument().addDocumentListener(sugg);

        this.addKeyListener(sugg);
    }
}
