package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.ui.api.MessageFormFactory;
import zhawmessenger.ui.api.MessagePlugin;
import zhawmessenger.ui.api.MessageWindowFactory;

import javax.swing.*;

/**
 */
public class SmsMessagePlugin implements MessagePlugin<Sms> {
    @Override
    public boolean doesHandle(Class<Sms> messageClass) {
        return false;  // FIXME
    }

    @Override
    public Class<Sms> getMessageClass() {
        return null;  // FIXME
    }

    @Override
    public String getName() {
        return null;  // FIXME
    }

    @Override
    public Icon getIcon() {
        return null;  // FIXME
    }

    @Override
    public MessageFactory getMessageFactory() {
        return null;  // FIXME
    }

    @Override
    public MessageWindowFactory getWindowFactory() {
        return null;  // FIXME
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return null;  // FIXME
    }
}
