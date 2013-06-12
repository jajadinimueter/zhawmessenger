package zhawmessenger.ui.impl.components.suggest;

import zhawmessenger.messagesystem.api.util.Finder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 */
public class Suggestor<T>
        implements KeyListener, DocumentListener {

    private final Window owner;
    private final JTextComponent textComponent;

    private final SuggestorLogic<T> logic;

    private final SuggesterValueExtractor<T> extractor;
    private final List<Finder<String, T>> finders;

    private final ListTableModel<T> tableModel;

    private final JWindow choicesDialog;
    private final JTable choicesList;

    private final List<SuggesterItemListener<T>> suggesterItemListeners;

    private int startPos = 0;
    private int endPos = 0;

    public Suggestor(Window owner, JTextComponent textComponent, SuggesterValueExtractor<T> extractor) {
        this(owner, textComponent, extractor, new ArrayList<Finder<String, T>>());
    }

    public Suggestor(Window owner,
                     JTextComponent textComponent,
                     SuggesterValueExtractor<T> extractor,
                     List<Finder<String, T>> finders) {

        suggesterItemListeners = new ArrayList<SuggesterItemListener<T>>();
        this.tableModel = new ListTableModel<T>(extractor);
        this.logic = new SuggestorLogic<T>();
        this.extractor = extractor;
        this.textComponent = textComponent;
        this.owner = owner;
        this.finders = finders;
        this.choicesDialog = new JWindow(owner);
        this.choicesDialog.setLocationRelativeTo(owner);
        this.choicesDialog.setFocusableWindowState(false);
        this.choicesDialog.setFocusable(false);
        this.choicesDialog.setSize(300, 200);
        this.choicesList = new JTable();
        this.choicesList.setTableHeader(null);
        this.choicesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(this.choicesList);
        listScroller.setPreferredSize(new Dimension(300, 200));
        this.choicesList.setModel(this.tableModel);
        this.choicesDialog.add(listScroller);
    }

    private Set<T> find() {
        SuggestorLogic.SearchRange range = logic.getSearchRange(textComponent.getText(),
                textComponent.getCaretPosition());

        String search = textComponent.getText().substring(
                range.getSearchFrom(), range.getSearchTo());

        System.out.println("Searching for " + search);

        final HashSet<T> found = new HashSet<T>();
        for (Finder<String, T> finder : finders) {
            found.addAll(finder.find(search));
        }

        return found;
    }

    class ListTableModel<T> extends AbstractTableModel {
        private final List<T> items;
        private final SuggesterValueExtractor<T> extractor;

        public ListTableModel(SuggesterValueExtractor<T> extractor) {
            this.items = new ArrayList<T>();
            this.extractor = extractor;
        }

        public void add(T item) {
            this.items.add(item);
            this.fireTableDataChanged();
        }

        public void replace(Collection<T> items) {
            this.items.clear();
            this.addAll(items);
            this.fireTableDataChanged();
        }

        public void addAll(Collection<T> items) {
            this.items.addAll(items);
            this.fireTableDataChanged();
        }

        public T get(int index) {
            return items.get(index);
        }

        public void clear() {
            this.items.clear();
            this.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return items.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return this.extractor.extract(this.items.get(rowIndex));
        }
    }

    private void showChoices() {
        Set<T> items = this.find();
        tableModel.replace(items);
        double x = owner.getX() + textComponent.getX() + 60;
        double y = owner.getY() + textComponent.getY() + 80;
        choicesDialog.setLocation((int)x, (int)y);
        if (items.size() > 0) {
            choicesList.setRowSelectionInterval(0,0);
        }
        choicesDialog.setVisible(items.size() > 0);
    }

    public void addSuggestorItemListener(SuggesterItemListener<T> suggesterItemListener) {
        this.suggesterItemListeners.add(suggesterItemListener);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.showChoices();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.showChoices();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.showChoices();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // FIXME
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (choicesDialog.isVisible()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (this.choicesDialog.isVisible()) {
                    int index = this.choicesList.getSelectedRow();
                    if (index >= 0) {
                        T item = tableModel.get(index);
                        for (SuggesterItemListener<T> sl : suggesterItemListeners) {
                            sl.itemFound(item);
                        }
                        this.textComponent.setText("");
                        this.choicesDialog.setVisible(false);
                        e.consume();
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int index = this.choicesList.getSelectedRow();
                if (index < tableModel.getRowCount() - 1) {
                    choicesList.setRowSelectionInterval(0, index + 1);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                int index = this.choicesList.getSelectedRow();
                if (index > 0) {
                    choicesList.setRowSelectionInterval(0, index - 1);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                choicesDialog.setVisible(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // FIXME
    }

}