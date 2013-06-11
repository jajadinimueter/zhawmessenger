package zhawmessenger.messagesystem.api.modules.email.message;

import zhawmessenger.messagesystem.api.contact.ContactProvider;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.remind.Remindable;

import java.util.List;

/**
 */
public interface Email extends Message<EmailContact>,
        Remindable<EmailContact> {

    void setText(String text);

    String getText();

    void setSubject(String subject);

    String getSubject();

    void setSender(EmailContact sender);

    EmailContact getSender();

    void addBcc(EmailContact bcc);

    List<EmailContact> getBccs();

    void addCc(EmailContact cc);

    List<EmailContact> getCcs();
}
