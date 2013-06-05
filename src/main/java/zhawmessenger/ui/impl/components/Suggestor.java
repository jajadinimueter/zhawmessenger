package zhawmessenger.ui.impl.components;

import zhawmessenger.messagesystem.api.util.Finder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class Suggestor<T> implements KeyListener, DocumentListener {
    private final Window owner;
    private final JTextArea textComponent;

    private final SuggestorLogic<T> logic;

    private final SuggesterValueExtractor<T> extractor;
    private final List<Finder<String,T>> finders;

    private final JDialog choicesDialog;
    private final JList choicesList;

    private int startPos = 0;
    private int endPos = 0;

    public Suggestor(Window owner, JTextArea textComponent, SuggesterValueExtractor<T> extractor) {
        this(owner, textComponent, extractor, new ArrayList<Finder<String, T>>());
    }

    public Suggestor(Window owner, JTextArea textComponent,
                     SuggesterValueExtractor<T> extractor, List<Finder<String, T>> finders) {
        this.logic = new SuggestorLogic<T>();
        this.extractor = extractor;
        this.textComponent = textComponent;
        this.owner = owner;
        this.finders = finders;
        this.choicesDialog = new JDialog(owner);
        this.choicesDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.choicesDialog.setLocationRelativeTo(owner);
        this.choicesDialog.setFocusableWindowState(false);
        this.choicesDialog.setFocusable(false);
        this.choicesDialog.setUndecorated(true);
        this.choicesDialog.setSize(200, 20);
        this.choicesList = new JList();
        this.choicesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.choicesList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(this.choicesList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        this.choicesDialog.add(listScroller);
    }

    private Set<T> find() {
        SuggestorLogic.SearchRange range = logic.getSearchRange(textComponent.getText(),
                textComponent.getCaretPosition());

        String search = textComponent.getText().substring(
                range.getSearchFrom(), range.getSearchTo());

        System.out.println("Searching for " + search);

        final HashSet<T> found = new HashSet<T>();
        for (Finder<String,T> finder : finders) {
            found.addAll(finder.find(search));
        }

        return found;
    }

    private void showChoices() {
        Set<T> items = this.find();
//        Point pt = textComponent.getCaret().getMagicCaretPosition();

//        if ( pt != null) {
        DefaultListModel mod = new DefaultListModel();
        for (T item : items) {
            mod.addElement(item);
        }
        choicesList.setModel(mod);
//        double x = owner.getX() + textComponent.getX() + pt.getX();
//        double y = owner.getY() + textComponent.getY() + pt.getY()
//                + choicesDialog.getHeight() + 50;
//            choicesDialog.setLocation((int)x, (int)y);
        choicesDialog.setLocation(100, 100);
//        choicesDialog.setVisible(items.size() > 0);
        if (items.size() > 0) {
            choicesList.setSelectedIndex(0);
        }
        choicesDialog.setVisible(true);
//        } else {
//            choicesDialog.setVisible(false);
//        }
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.choicesDialog.isVisible()) {
                ListModel mod = this.choicesList.getModel();
                int index = this.choicesList.getSelectedIndex();
                if ( index >= 0) {
                    //noinspection unchecked
                    T item = (T) mod.getElementAt(index);
                    SuggestorLogic.SearchRange range = logic.getSearchRange(textComponent.getText(),
                            textComponent.getCaretPosition());

                    textComponent.replaceRange(" <" + extractor.extract(item) + ">",
                            range.getReplaceFrom(), range.getReplaceTo());
                    e.consume();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // FIXME
    }

}