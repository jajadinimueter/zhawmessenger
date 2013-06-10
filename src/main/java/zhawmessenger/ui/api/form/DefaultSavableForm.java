package zhawmessenger.ui.api.form;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.CancelListener;
import zhawmessenger.ui.api.SaveListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class DefaultSavableForm<T extends Message>
        implements SavableForm<T> {

    private MessageForm<T> formPanel;
    private final JPanel mainPanel;

    private final List<SaveListener> saveListeners;
    private final List<CancelListener> cancelListeners;

    public DefaultSavableForm(MessageForm<T> formPanel) {
        this.formPanel = formPanel;

        mainPanel = new JPanel();

        JPanel buttonPanel = buildButtonPanel();

        saveListeners = new ArrayList<SaveListener>();
        cancelListeners = new ArrayList<CancelListener>();

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    protected JPanel buildButtonPanel() {
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
                    listener.saved(formPanel.getSavedMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CancelListener listener : cancelListeners) {
                    listener.canceled(formPanel.getMessage());
                }
            }
        });

        return buttonPanel;
    }

    @Override
    public void addSaveListener(SaveListener listener) {
        saveListeners.add(listener);
    }

    @Override
    public void addCancelListener(CancelListener listener) {
        cancelListeners.add(listener);
    }

    @Override
    public JPanel getForm() {
        return this.mainPanel;
    }
}