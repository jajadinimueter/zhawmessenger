package zhawmessenger.ui.impl.modules.mobilephone;

import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.ShortMessage;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.ui.api.ApplicationContext;
import zhawmessenger.ui.api.MessageForm;
import zhawmessenger.ui.api.form.FormBuilder;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.SendAtPanel;
import zhawmessenger.ui.impl.components.SenderField;
import zhawmessenger.ui.impl.components.StopperGridBagConstraintsChanger;

import javax.swing.*;
import java.awt.*;

/**
 */
public abstract class ShortMessageForm<T extends ShortMessage> extends MessageForm<T> {

    private final boolean withMms;
    private final ApplicationContext appContext;

    public ShortMessageForm(Window owner, T message, boolean withMms) {
        super(owner, message);
        this.withMms = withMms;
        this.appContext = DefaultApplicationContext.getInstance();

        this.buildForm();
    }

    protected void buildForm() {
        FormBuilder builder = new FormBuilder(this, new Insets(3,3,3,3), false);

        SenderField<MobilePhoneContact> senderField = builder.addComponent(new JLabel("Absender"),
                new SenderField<MobilePhoneContact>(
                        appContext.getUserLoggedIn().getMobilePhoneContacts()));

        JTextArea text = new JTextArea(1,1);
        builder.addField(new JScrollPane(text),
                new StopperGridBagConstraintsChanger());

        builder.addField(new SendAtPanel());
    }
}
