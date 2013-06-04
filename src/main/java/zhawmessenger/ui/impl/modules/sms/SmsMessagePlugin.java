package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.ui.api.MessageFormFactory;
import zhawmessenger.ui.api.MessagePlugin;
import zhawmessenger.ui.api.MessageWindowFactory;

import javax.swing.*;

/**
 */
public class SmsMessagePlugin implements MessagePlugin {
    @Override
    public boolean doesHandle(Class<? extends Message> messageClass) {
        return false;  // FIXME
    }

    @Override
    public Class<Email> getMessageClass() {
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
    public MessageWindowFactory getWindowFactory() {
        return null;  // FIXME
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return null;  // FIXME
    }
}
