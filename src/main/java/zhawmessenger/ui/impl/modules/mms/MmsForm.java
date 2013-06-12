package zhawmessenger.ui.impl.modules.mms;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessageForm;

import java.awt.*;

/**
 */
public class MmsForm extends ShortMessageForm<Mms> {

    public MmsForm(Window owner, Mms message, boolean withMms,
                   Finder<String, DisplayableContactProvider> finder) {
        super(owner, message, withMms, finder);
    }

    @Override
    public Mms getMessage() {
        return super.getMessage();
    }

    @Override
    public Mms getSavedMessage() {
        return super.getSavedMessage();
    }
}
