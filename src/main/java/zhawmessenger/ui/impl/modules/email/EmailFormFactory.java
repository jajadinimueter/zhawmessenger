package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.ui.api.AbstractFormFactory;
import zhawmessenger.ui.api.CancelListener;
import zhawmessenger.ui.api.SaveListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 */
public class EmailFormFactory
        extends AbstractFormFactory<Email> {

    @Override
    public JPanel createForm(final Email message) {
        // build the actual form
        return new EmailForm(message);
    }

    class EmailForm extends JPanel {
        private Email message;

        public EmailForm(Email message) {
            this.message = message;

            this.initComponent();
        }

        protected void initComponent() {
            JPanel form = new JPanel();

            Border border = BorderFactory.createLineBorder(Color.gray);

            this.setLayout(new BorderLayout());

            form.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.weightx = 1.0;
            gbc.gridwidth = 2;

            gbc.gridx = 0;
            gbc.gridy = 0;
            form.add(new JLabel("Absender"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            form.add(new JComboBox(new DefaultComboBoxModel(new String[]{"Foo", "Bar"})), gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            form.add(new JLabel("Empfänger"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 1.0;
            JTextArea reciepients = new EmailReceiverTextArea(10, 1);
            reciepients.setBorder(border);
            form.add(reciepients, gbc);
            gbc.weighty = 0.0;

            gbc.gridx = 0;
            gbc.gridy = 4;
            form.add(new JLabel("Anhänge"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            gbc.weightx = 0.8;
            form.add(new JTextField(), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.2;
            form.add(new JButton("Datei"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            form.add(new JLabel("Text"), gbc);

            gbc.gridy = 7;
            gbc.weighty = 1.0;
            final JTextArea text = new JTextArea(15, 1);
            text.setText(message.getText());
            text.setBorder(border);
            form.add(text, gbc);
            gbc.weighty = 0.0;

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            GridBagConstraints buttonPanelGbc = new GridBagConstraints();
            buttonPanelGbc.insets = new Insets(5, 5, 5, 5);
            buttonPanelGbc.gridx = 0;
            buttonPanelGbc.gridy = 0;
            buttonPanelGbc.weightx = 1.0;
            buttonPanel.add(new JPanel(), buttonPanelGbc); // spacer
            buttonPanelGbc.weightx = 0;
            buttonPanelGbc.gridx = 1;
            buttonPanelGbc.gridy = 0;
            JButton cancelButton = new JButton("Abbrechen");
            buttonPanel.add(cancelButton, buttonPanelGbc);
            buttonPanelGbc.gridx = 2;
            buttonPanelGbc.gridy = 0;
            JButton saveButton = new JButton("Speichern");
            buttonPanel.add(saveButton, buttonPanelGbc);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (SaveListener listener : saveListeners) {
                        message.setText(text.getText());
                        listener.saved(message);
                    }
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (CancelListener listener : cancelListeners) {
                        listener.canceled(message);
                    }
                }
            });

            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 2;
            gbc.weighty = 1.0;
            form.add(buttonPanel, gbc);
            this.add(form);
//
//            testButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    for (SaveListener listener : saveListeners) {
//                        listener.saved(message);
//                    }
//                }
//            });

        }

    }

}
