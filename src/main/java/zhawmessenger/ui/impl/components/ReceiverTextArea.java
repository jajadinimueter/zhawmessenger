package zhawmessenger.ui.impl.components;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.impl.components.suggest.SuggesterItemListener;
import zhawmessenger.ui.impl.components.suggest.SuggesterValueExtractor;
import zhawmessenger.ui.impl.components.suggest.Suggestor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 */
public class ReceiverTextArea extends JPanel {
    private final JTextField searchField;
    private final JTable resultTable;
    private final ReceiverTableModel receiverTableModel;

    class ReceiverTableModel extends AbstractTableModel {
        private List<DisplayableContactProvider> contacts;

        public ReceiverTableModel() {
            contacts = new ArrayList<DisplayableContactProvider>();
        }

        public List<DisplayableContactProvider> getContacts() {
            return contacts;
        }

        public void setContacts(List<DisplayableContactProvider> contacts) {
            // have to copy the list
            this.contacts.clear();
            for (DisplayableContactProvider contact : contacts) {
                this.contacts.add(contact);
            }
            this.fireTableDataChanged();
        }

        public void removeContact(int index) {
            contacts.remove(index);
            this.fireTableDataChanged();
        }

        public void addContact(DisplayableContactProvider contact) {
            this.contacts.add(contact);
            this.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return contacts.size();
        }

        @Override
        public int getColumnCount() {
            return 1;  // FIXME
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return contacts.get(rowIndex).getDisplay();
        }
    }

    public ReceiverTextArea(Window owner,
                            List<Finder<String, DisplayableContactProvider>> finders) {

        FormBuilder builder = new FormBuilder(this, new Insets(3, 3, 3, 3), true);

        searchField = new JTextField();

        Suggestor<DisplayableContactProvider> sugg = new Suggestor<DisplayableContactProvider>(owner, searchField,
                new SuggesterValueExtractor<DisplayableContactProvider>() {
                    @Override
                    public String extract(DisplayableContactProvider item) {
                        return item.getDisplay();
                    }
                }, finders);

        sugg.addSuggestorItemListener(new SuggesterItemListener<DisplayableContactProvider>() {
            @Override
            public void itemFound(DisplayableContactProvider item) {
                receiverTableModel.addContact(item);
            }
        });

        searchField.addKeyListener(sugg);

        searchField.getDocument().addDocumentListener(sugg);

        receiverTableModel = new ReceiverTableModel();
        resultTable = new JTable(receiverTableModel);

        resultTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int index = resultTable.getSelectedRow();
                    if ( index >= 0 ) {
                        receiverTableModel.removeContact(index);
                    }
                    e.consume();
                }
            }
        });

        builder.addField(searchField);
        builder.addField(resultTable);
    }

    public void setContactProviders(List<DisplayableContactProvider> cp) {
        receiverTableModel.setContacts(cp);
    }

    public List<DisplayableContactProvider> getContactProviders() {
        return receiverTableModel.getContacts();
    }
}
