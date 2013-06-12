package zhawmessenger.ui.impl.components;

import javax.swing.*;

class TextAreaWithScroll extends JTextArea {
    private JScrollPane scrollPane;

    public TextAreaWithScroll(int vsbPolicy, int hsbPolicy) {
        this.setRows(10);
        scrollPane = new JScrollPane(this, vsbPolicy, hsbPolicy);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}