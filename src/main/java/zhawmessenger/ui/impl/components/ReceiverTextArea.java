package zhawmessenger.ui.impl.components;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.util.Finder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 */
public class ReceiverTextArea extends JTextArea {
    public <T extends DisplayableContactProvider> ReceiverTextArea(Window owner,
                            List<Finder<String, T>> finders,
                            int rows, int columns) {
        super(rows, columns);

        Suggestor<T> sugg =
                new Suggestor<T>(owner, this,
                        new SuggesterValueExtractor<T>() {
                            @Override
                            public String extract(T item) {
                                return item.getDisplay();
                            }
                        }, finders);

        this.getDocument().addDocumentListener(sugg);

        this.addKeyListener(sugg);
    }
}
