package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.api.remind.Reminder;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.messagesystem.impl.contact.MemoryEmailContactFinder;
import zhawmessenger.messagesystem.impl.modules.email.contact.EmailContactImpl;
import zhawmessenger.messagesystem.impl.modules.email.persistance.MemoryEmailContactRepository;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.FormBuilder;
import zhawmessenger.ui.api.form.FormBuilderConstraints;
import zhawmessenger.ui.api.form.GridBagConstraintsChanger;
import zhawmessenger.ui.api.form.GridBagConstraintsChangerAdapter;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class EmailFormFactory
        extends AbstractFormFactory<Email> {

    private EmailContactRepository contactRepository;

    public EmailFormFactory(EmailContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public SavableForm<Email> createForm(final Window owner, final Email message) {
        // build the actual form
        return new DefaultSavableForm<Email>(new EmailForm(owner, message));
    }

    class EmailForm extends MessageForm<Email> {
        private final ArrayList<Finder<String, DisplayableContactProvider>> finders;

        private ApplicationContext appContext;
        private JComboBox senderField;
        private ReceiverTextArea receiverTextArea;
        private JTextArea text;
        private JCheckBox sendImmediately;
        private DateTimeChooser sendAtChooser;
        private JCheckBox noReminder;
        private DateTimeChooser remindDateChooser;


        public EmailForm(Window owner, Email message) {
            super(owner, message)
            ;
            this.finders = new ArrayList<Finder<String, DisplayableContactProvider>>();

            appContext = DefaultApplicationContext.getInstance();

            this.finders.add(new MemoryEmailContactFinder(contactRepository));

            this.buildForm();
        }

        @Override
        public Email getSavedMessage() {
            message.setSender((EmailContact) senderField.getModel().getSelectedItem());
            message.setText(text.getText());
//            message.setSendTime(sendAtChooser.getDate().getTime());
            return this.getMessage();
        }

        protected void buildForm() {
            FormBuilder builder = new FormBuilder(this, new Insets(3,3,3,3), false);

            senderField = builder.addComponent(new JLabel("Absender"),
                    new SenderField<EmailContact>(
                            appContext.getUserLoggedIn().getEmailContacts()));

            JScrollPane receiversField = builder.addComponent(new JLabel("Empfänger"),
                    new JScrollPane(new ReceiverTextArea(owner, finders, 1, 1)),
                    new GridBagConstraintsChangerAdapter() {
                        @Override
                        public GridBagConstraints changeField(Component field, GridBagConstraints gbc) {
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.weighty = 1.0;
                            return gbc;
                        }

                        @Override
                        public GridBagConstraints changeFieldContainer(JPanel fieldContainer, GridBagConstraints gbc) {
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.weighty = .2;
                            return gbc;
                        }
                    });

            receiversField.setBorder(new LineBorder(Color.GRAY));

            FormBuilderConstraints leftAlign = new FormBuilderConstraints(
                    FormBuilderConstraints.Align.LEFT);

            // text
            text = new JTextArea(1, 1);
            builder.addField(new JScrollPane(text),
                    new GridBagConstraintsChangerAdapter() {
                        @Override
                        public GridBagConstraints changeField(Component field, GridBagConstraints gbc) {
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.weighty = 1.0;
                            return gbc;
                        }

                        @Override
                        public GridBagConstraints changeFieldContainer(JPanel fieldContainer,
                                                                       GridBagConstraints gbc) {
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.weighty = 1.0;
                            return gbc;
                        }
                    });

            receiversField.setBorder(new LineBorder(Color.GRAY));

            SendAtPanel sendAtPanel = builder.addField(new SendAtPanel());
        }

        @Override
        public Email getMessage() {
            return this.message;
        }
    }
}
