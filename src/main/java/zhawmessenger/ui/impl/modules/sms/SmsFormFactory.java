package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.ui.api.*;

import java.awt.*;

/**
 */
public class SmsFormFactory
        extends AbstractFormFactory<Sms> {

    @Override
    public SavableForm<Sms> createForm(Window owner, Sms message) {
        return new DefaultSavableForm<Sms>(new SmsForm(owner, message));
    }


}
