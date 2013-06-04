package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.impl.modules.email.contact.EmailContactImpl;
import zhawmessenger.messagesystem.impl.contact.MemoryEmailContactFinder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 */
public class EmailReceiverTextArea extends JTextArea {
    private final MemoryEmailContactFinder emailFinder;

    public EmailReceiverTextArea(int rows, int columns){
        super(rows, columns);
        final List<EmailContact> contacts =
                new ArrayList<EmailContact>();
        contacts.add(new EmailContactImpl("foo@bar.ch"));
        Suggestor sugg = new Suggestor();
        this.emailFinder = new MemoryEmailContactFinder(contacts);
        this.getDocument().addDocumentListener(sugg);
        this.addKeyListener(sugg);
    }

    private Window getParentWindow() {
        Component p = this.getParent();
        while (p != null) {
            if (p instanceof Window) {
                return (Window) p;
            }
            p = p.getParent();
        }
        return null;
    }

    class Suggestor implements KeyListener, DocumentListener {
        private JDialog choicesDialog;
        private JComboBox choicesCombo;
        private int startPos = 0;
        private int endPos = 0;

        private Set<EmailContact> find() {
            String text = getText();

            this.startPos = 0;
            this.endPos = text.length() - 1;

            if (this.endPos < 0) {
                this.endPos = 0;
            }

            if (text.charAt(this.endPos) == ',') {
                this.endPos -= 1;
            }

            //           v
            // |a|b|c|d|,|a|b|,|
            // 0 1 2 3 4 5 6 7 8
            //  0 1 2 3 4 5 6 7
            // text.length() = 4

            int pos = getCaretPosition();

            if (pos > this.endPos) {
                pos = this.endPos;
            }

            for (int i = pos; i >= 0; i--) {
                if (text.charAt(i) == ',') {
                    this.startPos = i + 1;
                    break;
                }
            }

            if (pos <= this.endPos - 1) {
                for (int i = pos + 1; i <= text.length() - 1; i++) {
                    if (text.charAt(i) == ',') {
                        this.endPos = i;
                        break;
                    }
                }
            }

            String search = text.substring(this.startPos, this.endPos)
                    .toLowerCase();

            return emailFinder.find(search);
        }

        private void createChoicesDialog() {
            if( this.choicesDialog == null  ) {
                Window parentWindow = getParentWindow();
                this.choicesDialog = new JDialog(parentWindow);
                choicesDialog.setLocationRelativeTo(parentWindow);
                this.choicesDialog.setFocusableWindowState(false);
                this.choicesDialog.setFocusable(false);
                this.choicesDialog.setUndecorated(true);
                this.choicesDialog.setSize(200, 20);
                this.choicesCombo = new JComboBox();
                this.choicesDialog.add(choicesCombo);
                parentWindow.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentMoved(ComponentEvent e) {
                        choicesDialog.setVisible(false);
                    }
                });
            }
        }

        private void showChoices() {
            this.createChoicesDialog();
            Set<EmailContact> contacts = this.find();
            Point pt = getCaret().getMagicCaretPosition();
            Window parentWindow = getParentWindow();
            if ( pt != null && parentWindow != null) {
                ComboBoxModel mod = new DefaultComboBoxModel(contacts.toArray());
                choicesCombo.setModel(mod);
                double x = parentWindow.getX() + getX() + pt.getX();
                double y = parentWindow.getY() + getY() + pt.getY()
                        + choicesDialog.getHeight() + 50;
                choicesDialog.setLocation((int)x, (int)y);
                choicesDialog.setVisible(contacts.size() > 0);

            } else {
                choicesDialog.setVisible(false);
            }
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
                    ComboBoxModel mod = this.choicesCombo.getModel();
                    Object o = mod.getSelectedItem();
                    if (o == null) {
                        if ( mod.getSize() > 0 ) {
                            o = mod.getElementAt(0);
                        }
                    }
                    if (o != null) {
                        EmailContact c = (EmailContact) mod.getElementAt(0);
                        replaceRange(c.getValue(), this.startPos, this.endPos);
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
}
