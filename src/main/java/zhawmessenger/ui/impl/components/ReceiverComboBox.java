package zhawmessenger.ui.impl.components;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.util.Finder;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ReceiverComboBox extends JComboBox implements ActionListener {
    private final List<Finder<String, DisplayableContactProvider>> finders;
    private final DefaultComboBoxModel comboModel;

    public ReceiverComboBox(List<Finder<String, DisplayableContactProvider>> finders) {
        this.finders = finders;
        comboModel = new DefaultComboBoxModel();
        comboModel.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                System.out.println("Contents changed");
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                System.out.println("Contents changed");
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                System.out.println("Contents changed");
            }
        });
        this.setModel(comboModel);
        this.setEditable(true);
        this.setModel(new DefaultComboBoxModel());
        this.addActionListener(this);
        this.addItemListener(new MyItemListener());
        this.addKeyListener(new MyKeyListener());
    }

    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (isDisplayable()) setPopupVisible(true);
            find();
        }
    }

    private void find() {
        List<DisplayableContactProvider> found = new ArrayList<DisplayableContactProvider>();
        if ( getSelectedItem() instanceof String ) {
            for (Finder<String, ? extends DisplayableContactProvider> finder : finders) {
                found.addAll(finder.find((String) getSelectedItem()));
            }
        }

        comboModel.removeAllElements();

        for (DisplayableContactProvider d : found) {
            comboModel.addElement(d.getDisplay());
        }
    }

    class MyItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            find();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() instanceof JComboBox ) {
            JComboBox cb = (JComboBox)e.getSource();
        }
    }
}
