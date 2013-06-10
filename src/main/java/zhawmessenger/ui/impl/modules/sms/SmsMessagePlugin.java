package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.messagesystem.impl.modules.mobilephone.message.SmsImpl;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.plugin.MessagePlugin;
import zhawmessenger.ui.api.util.DefaultMessageWindowFactory;

import javax.swing.*;
import java.util.UUID;

/**
 */
public class SmsMessagePlugin implements MessagePlugin<Sms> {
    @Override
    public boolean doesHandle(Class<Sms> messageClass) {
        return getMessageClass().isAssignableFrom(messageClass);
    }

    @Override
    public Class<Sms> getMessageClass() {
        return Sms.class;
    }

    @Override
    public String getName() {
        return "SMS";
    }

    @Override
    public Icon getIcon() {
        return null;  // FIXME
    }

    @Override
    public ItemFormatter<Sms> getPreviewFormatter() {
        return new ItemFormatter<Sms>() {
            @Override
            public String format(Sms message) {
                return message.getText();
            }
        };
    }

    @Override
    public MessageFactory getMessageFactory() {
        return new MessageFactory() {
            @Override
            public Message createMessage() {
                return new SmsImpl(UUID.randomUUID());
            }
        };
    }

    @Override
    public MessageWindowFactory getWindowFactory() {
        return new DefaultMessageWindowFactory();
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new SmsFormFactory();
    }
}
