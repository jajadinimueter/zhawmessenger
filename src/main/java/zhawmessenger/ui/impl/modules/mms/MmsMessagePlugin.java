package zhawmessenger.ui.impl.modules.mms;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Mms;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;
import zhawmessenger.messagesystem.impl.modules.mobilephone.message.MmsImpl;
import zhawmessenger.messagesystem.impl.persistance.MemoryContactFinder;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.DefaultSavableForm;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.form.SavableForm;
import zhawmessenger.ui.api.plugin.MessagePlugin;
import zhawmessenger.ui.api.util.DefaultMessageWindowFactory;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessagePlugin;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 */
public class MmsMessagePlugin
        extends ShortMessagePlugin<Mms>
        implements MessagePlugin<Mms> {

    public MmsMessagePlugin(List<SearchableRepository<? extends DisplayableContactProvider>> repos) {
        super(repos);
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
                return new DefaultSavableForm<Mms>(new MmsForm(owner, message, true,
                        new MemoryContactFinder(repos)));
            }
        };
    }
}
