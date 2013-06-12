package zhawmessenger.ui.impl.modules.mobilephone;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.ShortMessage;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.messagesystem.impl.persistance.MemoryContactFinder;
import zhawmessenger.ui.api.ApplicationContext;
import zhawmessenger.ui.api.form.MessageForm;
import zhawmessenger.ui.api.form.validator.ComboBoxRequiredValidator;
import zhawmessenger.ui.api.form.validator.CompoundValidator;
import zhawmessenger.ui.api.form.validator.TextFieldRequiredValidator;
import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.*;
import zhawmessenger.ui.impl.validator.DatePanelValidator;
import zhawmessenger.ui.impl.validator.ReceiverRequiredValidator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

/**
 */
public abstract class ShortMessageForm<T extends ShortMessage> extends MessageForm<T> {

    private final boolean withMms;
    private final ApplicationContext appContext;
    private final Finder<String, DisplayableContactProvider> finder;

    protected CompoundValidator validator;
    protected ReceiverTextArea receiverTextArea;
    protected SenderField<MobilePhoneContact> senderField;
    protected JTextArea text;
    protected SendAtPanel sendAtPanel;
    protected RemindAtPanel remindAtPanel;

    public ShortMessageForm(Window owner, T message,
                            boolean withMms, Finder<String, DisplayableContactProvider> finder) {
        super(owner, message);

        validator = new CompoundValidator();
        this.finder = finder;
        this.withMms = withMms;
        this.appContext = DefaultApplicationContext.getInstance();

        this.buildForm();
    }

    protected void buildForm() {
        FormBuilder builder = new FormBuilder(this, new Insets(3, 3, 3, 3), false);

        senderField = builder.addComponent(new JLabel("Absender"),
                new SenderField<MobilePhoneContact>(
                        appContext.getUserLoggedIn().getMobilePhoneContacts()));

        validator.addValidator(new ComboBoxRequiredValidator(senderField));

        //noinspection unchecked
        receiverTextArea = builder.addComponent(new JLabel("Empfänger"),
                new ReceiverTextArea(owner, Arrays.asList(finder)));

        receiverTextArea.setBorder(new LineBorder(Color.GRAY));

        validator.addValidator(new ReceiverRequiredValidator(receiverTextArea));

        text = new JTextArea(1, 1);
        builder.addField(new JScrollPane(text),
                new StopperGridBagConstraintsChanger());

        validator.addValidator(new TextFieldRequiredValidator(text));

        sendAtPanel = builder.addField(new SendAtPanel());

        remindAtPanel = builder.addField(new RemindAtPanel());
    }

    @Override
    public boolean validateFields() {
        return validator.validate();
    }

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public T getSavedMessage() {
        message.setText(text.getText());
        for (DisplayableContactProvider cp : receiverTextArea.getContactProviders()) {
            message.addContactProvider(cp);
        }
        message.setSendDate(sendAtPanel.getDate());
        message.setReminderDate(remindAtPanel.getDate());
        return message;
    }
}
