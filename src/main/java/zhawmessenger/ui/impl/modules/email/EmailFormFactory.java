package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.messagesystem.impl.contact.MemoryEmailContactFinder;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.DefaultSavableForm;
import zhawmessenger.ui.api.form.MessageForm;
import zhawmessenger.ui.api.form.SavableForm;
import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;
import zhawmessenger.ui.api.formbuilder.GridBagConstraintsChangerAdapter;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

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
        // build the actual formbuilder
        return new DefaultSavableForm<Email>(new EmailForm(owner, message));
    }

    class EmailForm extends MessageForm<Email> {
        private final ArrayList<Finder<String, DisplayableContactProvider>> finders;

        private SendAtPanel sendAtPanel;
        private ApplicationContext appContext;
        private JComboBox senderField;
        private ReceiverTextArea receiverTextArea;
        private JTextArea text;
        private JTextField subjectField;
        private JCheckBox sendImmediately;
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
            message.setSendTime(sendAtPanel.getSendDate().getTime());
            message.setSubject(subjectField.getText());
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

            subjectField = builder.addComponent(new JLabel("Betreff"),
                    new JTextField());

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

            sendAtPanel = builder.addField(new SendAtPanel());
        }

        @Override
        public Email getMessage() {
            return this.message;
        }
    }
}
