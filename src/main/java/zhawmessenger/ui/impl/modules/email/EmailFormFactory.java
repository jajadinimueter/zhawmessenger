package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.messagesystem.impl.contact.MemoryEmailContactFinder;
import zhawmessenger.messagesystem.impl.modules.email.contact.EmailContactImpl;
import zhawmessenger.ui.api.AbstractFormFactory;
import zhawmessenger.ui.api.DefaultSavableForm;
import zhawmessenger.ui.api.MessageForm;
import zhawmessenger.ui.api.SavableForm;
import zhawmessenger.ui.impl.components.ComponentFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class EmailFormFactory
        extends AbstractFormFactory<Email> {

    @Override
    public SavableForm<Email> createForm(final Window owner, final Email message) {
        // build the actual form
        return new DefaultSavableForm<Email>(
                owner, new EmailForm(owner, message), message);
    }

    class EmailForm extends MessageForm<Email> {
        private final List<Finder<String, EmailContact>> finders;
        private final Window owner;
        private final Email message;

        public EmailForm(Window owner, Email message) {
            this.message = message;
            this.owner = owner;
            this.finders = new ArrayList<Finder<String, EmailContact>>();

            ArrayList<EmailContact> contacts = new ArrayList<EmailContact>();
            contacts.add(new EmailContactImpl("foo@bar.ch"));
            contacts.add(new EmailContactImpl("bar@baz.ch"));
            this.finders.add(new MemoryEmailContactFinder(contacts));

            this.buildFormPanel();
        }

        protected void buildFormPanel() {
            Border border = BorderFactory.createLineBorder(Color.gray);

            this.setLayout(new BorderLayout());

            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.weightx = 1.0;
            gbc.gridwidth = 2;

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(new JLabel("Absender"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(new JComboBox(new DefaultComboBoxModel(
                    new String[]{"Foo", "Bar"})), gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.add(new JLabel("Empfänger"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 1.0;
            JTextArea reciepients = new EmailReceiverTextArea(
                    this.owner, this.finders, 10, 1);
            reciepients.setBorder(border);
            this.add(reciepients, gbc);
            gbc.weighty = 0.0;

            gbc.gridx = 0;
            gbc.gridy = 4;
            this.add(ComponentFactory.buildDatePanel(new Date()), gbc);
//            form.add(new JFormattedTextField(new SimpleDateFormat("dd.mm.yyyy hh:mm")), gbc);
//            form.add(new JLabel("Sendedatum"));
//            form.add(new JLabel("Anhänge"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            gbc.weightx = 0.8;
            this.add(new JTextField(), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.2;
            this.add(new JButton("Datei"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            this.add(new JLabel("Text"), gbc);

            gbc.gridy = 7;
            gbc.weighty = 1.0;
            final JTextArea text = new JTextArea(15, 1);
            text.setText(this.getMessage().getText());
            text.setBorder(border);
            this.add(text, gbc);
            gbc.weighty = 0.0;
        }

        @Override
        public Email getSavedMessage() {
            // fixme: save message
            return this.getMessage();
        }

        @Override
        public Email getMessage() {
            return this.message;
        }
    }
}
