package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.modules.mobilephone.contact.MobilePhoneContact;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.ui.api.ApplicationContext;
import zhawmessenger.ui.api.MessageForm;
import zhawmessenger.ui.api.form.FormBuilder;
import zhawmessenger.ui.impl.DefaultApplicationContext;
import zhawmessenger.ui.impl.components.SendAtPanel;
import zhawmessenger.ui.impl.components.SenderField;
import zhawmessenger.ui.impl.components.StopperGridBagConstraintsChanger;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessageForm;

import javax.swing.*;
import java.awt.*;

/**
 * Form which can be used for sms and mms
 */
public class SmsForm extends ShortMessageForm<Sms> {
    public SmsForm(Window owner, Sms message) {
        super(owner, message, false);
    }

    @Override
    public Sms getMessage() {
        return null;  // FIXME
    }

    @Override
    public Sms getSavedMessage() {
        return null;  // FIXME
    }
}
