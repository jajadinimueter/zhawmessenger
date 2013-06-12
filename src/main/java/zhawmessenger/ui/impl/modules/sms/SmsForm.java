package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessageForm;

import java.awt.*;

/**
 * Form which can be used for sms and mms
 */
public class SmsForm extends ShortMessageForm<Sms> {

    public SmsForm(Window owner, Sms message, boolean withMms,
                   Finder<String, DisplayableContactProvider> finder) {
        super(owner, message, withMms, finder);
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
