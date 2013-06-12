package zhawmessenger.ui.impl.modules.sms;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.Sms;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;
import zhawmessenger.messagesystem.impl.modules.mobilephone.message.SmsImpl;
import zhawmessenger.messagesystem.impl.persistance.MemoryContactFinder;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.DefaultSavableForm;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.form.SavableForm;
import zhawmessenger.ui.api.plugin.MessagePlugin;
import zhawmessenger.ui.api.util.DefaultMessageWindowFactory;
import zhawmessenger.ui.impl.modules.mobilephone.ShortMessagePlugin;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 */
public class SmsMessagePlugin extends ShortMessagePlugin<Sms> {
    public SmsMessagePlugin(List<SearchableRepository<? extends DisplayableContactProvider>> repos) {
        super(repos);
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
        return new AbstractFormFactory<Sms>() {
            @Override
            public SavableForm<Sms> createForm(Window owner, Sms message) {
                return new DefaultSavableForm<Sms>(new SmsForm(owner, message, false, new
                        MemoryContactFinder(repos)));
            }
        };
    }
}
