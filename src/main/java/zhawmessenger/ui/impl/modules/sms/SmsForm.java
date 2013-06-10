package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessageForm;

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
