package zhawmessenger.messagesystem.api.message;

import zhawmessenger.messagesystem.api.contact.EmailContact;

import java.util.List;

/**
 */
public interface Email extends Message<EmailContact> {

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
