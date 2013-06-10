package zhawmessenger.ui.impl.modules.email;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.GroupRepository;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.email.message.Email;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.impl.modules.email.message.EmailImpl;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.plugin.MessagePlugin;
import zhawmessenger.ui.api.util.DefaultMessageWindowFactory;

import javax.swing.*;
import java.util.UUID;

/**
 */
public class EmailMessagePlugin implements MessagePlugin<Email> {

    private EmailContactRepository contactRepository;
    private PersonRepository personRepository;
    private GroupRepository groupRepository;

    public EmailMessagePlugin(EmailContactRepository contactRepository,
                              PersonRepository personRepository,
                              GroupRepository groupRepository) {

        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
        this.groupRepository = groupRepository;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<Email> getMessageClass() {
        return Email.class;
    }

    @Override
    public boolean doesHandle(Class<Email> messageClass) {
        return Email.class.isAssignableFrom(messageClass);
    }

    public String getName() {
        return "Email";
    }

    public Icon getIcon() {
        return null;
    }

    @Override
    public ItemFormatter<Email> getPreviewFormatter() {
        return new ItemFormatter<Email>() {
            @Override
            public String format(Email message) {
                return String.format("<html>" +
                        "<b>Subject</b>: %s <br>" +
                        "<b>Receivers</b>: %s <br>" +
                        "<p>%s</p>" +
                        "</html>",
                        message.getSubject(),
                        message.getReceivers(),
                        message.getText());
            }
        };
    }

    @Override
    public MessageFactory getMessageFactory() {
        return new MessageFactory() {
            @Override
            public Message createMessage() {
                return new EmailImpl(UUID.randomUUID());
            }
        };
    }

    @Override
    public MessageFormFactory getFormFactory() {
        return new EmailFormFactory(contactRepository);
    }

    public MessageWindowFactory getWindowFactory() {
        return new DefaultMessageWindowFactory(1000, 700);
    }

}
