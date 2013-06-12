package zhawmessenger.ui.impl.modules.mobilephone;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.ShortMessage;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.messagesystem.impl.persistance.MemoryContactFinder;
import zhawmessenger.ui.api.ApplicationContext;
import zhawmessenger.ui.api.form.MessageForm;
import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.ReceiverTextArea;
import zhawmessenger.ui.impl.components.SendAtPanel;
import zhawmessenger.ui.impl.components.SenderField;
import zhawmessenger.ui.impl.components.StopperGridBagConstraintsChanger;

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

    protected ReceiverTextArea receiverTextArea;
    protected SenderField<MobilePhoneContact> senderField;
    protected JTextArea text;

    public ShortMessageForm(Window owner, T message,
                            boolean withMms, Finder<String, DisplayableContactProvider> finder) {
        super(owner, message);

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

        //noinspection unchecked
        receiverTextArea = builder.addComponent(new JLabel("Empfänger"),
                new ReceiverTextArea(owner, Arrays.asList(finder)));

        receiverTextArea.setBorder(new LineBorder(Color.GRAY));

        text = new JTextArea(1, 1);
        builder.addField(new JScrollPane(text),
                new StopperGridBagConstraintsChanger());

        builder.addField(new SendAtPanel());
    }

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public T getSavedMessage() {
        message.setText(text.getText());
        return message;

    }
}
