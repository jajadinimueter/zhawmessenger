package zhawmessenger.ui.impl.components;

import javax.swing.*;

class JTextAreaWithScroll extends JTextArea {
    private JScrollPane scrollPane;

    public JTextAreaWithScroll(int vsbPolicy, int hsbPolicy) {
        this.setRows(10);
        scrollPane = new JScrollPane(this, vsbPolicy, hsbPolicy);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}