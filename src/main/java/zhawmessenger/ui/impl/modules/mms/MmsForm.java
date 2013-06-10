package zhawmessenger.ui.impl.modules.mms;

import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessageForm;

import java.awt.*;

/**
 */
public class MmsForm extends ShortMessageForm<Mms> {
    public MmsForm(Window owner, Mms message) {
        super(owner, message, true);
    }

    @Override
    public Mms getMessage() {
        return null;  // FIXME
    }

    @Override
    public Mms getSavedMessage() {
        return null;  // FIXME
    }
}
