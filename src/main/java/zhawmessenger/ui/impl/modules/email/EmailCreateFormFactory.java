package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.message.Email;
import zhawmessenger.messagesystem.impl.message.EmailImpl;
import zhawmessenger.ui.api.AbstractCreateForm;
import zhawmessenger.ui.api.SaveListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 */
public class EmailCreateFormFactory
        extends AbstractCreateForm<Email> {

    @Override
    public JPanel createForm(Email message) {
        // build the actual form
        JPanel formPanel;
        JButton testButton;

        formPanel = new JPanel();

        formPanel.setLayout(new GridBagLayout());
        formPanel.add(new JLabel("Test-Label"));

        testButton = new JButton("Test-Button");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (SaveListener listener : saveListeners) {
                    listener.saved(new EmailImpl());
                }
            }
        });

        formPanel.add(testButton);
        return formPanel;    }

    @Override
    public JPanel createForm() {
        return this.createForm(null);
    }
}
