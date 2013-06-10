package zhawmessenger.ui.impl.modules.mms;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;
import zhawmessenger.messagesystem.impl.modules.mobilephone.message.MmsImpl;
import zhawmessenger.ui.api.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 */
public class MmsMessagePlugin
        implements MessagePlugin<Mms> {

    @Override
    public boolean doesHandle(Class<Mms> messageClass) {
        return Mms.class.isAssignableFrom(messageClass);
    }

    @Override
    public Class<Mms> getMessageClass() {
        return Mms.class;
    }

    @Override
    public String getName() {
        return "MMS";  // FIXME
    }

    @Override
    public Icon getIcon() {
        return null;  // FIXME
    }

    @Override
    public ItemFormatter<Mms> getPreviewFormatter() {
        throw new NotImplementedException();
    }

    @Override
    public MessageFactory getMessageFactory() {
        return new MessageFactory() {
            @Override
            public Message createMessage() {
                return new MmsImpl(UUID.randomUUID());
            }
        };
    }

    @Override
    public MessageWindowFactory getWindowFactory() {
        return new DefaultMessageWindowFactory();
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new AbstractFormFactory<Mms>() {
            @Override
            public SavableForm<Mms> createForm(Window owner, Mms message) {
                return new DefaultSavableForm<Mms>(new MmsForm(owner, message));
            }
        };
    }
}
