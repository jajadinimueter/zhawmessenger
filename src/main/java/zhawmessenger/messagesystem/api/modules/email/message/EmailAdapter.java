package zhawmessenger.messagesystem.api.modules.email.message;

import zhawmessenger.messagesystem.api.message.AbstractMessage;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;

import java.util.List;

/**
 */
public class EmailAdapter extends AbstractMessage<EmailContact> implements Email {
    private String subject;
    private EmailContact sender;
    private List<EmailContact> bccs;
    private List<EmailContact> ccs;

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public void setSender(EmailContact sender) {
        this.sender = sender;
    }

    @Override
    public EmailContact getSender() {
        return this.sender;
    }

    @Override
    public void addBcc(EmailContact bcc) {
        this.bccs.add(bcc);
    }

    @Override
    public List<EmailContact> getBccs() {
        return this.bccs;
    }

    @Override
    public void addCc(EmailContact cc) {
        this.ccs.add(cc);
    }

    @Override
    public List<EmailContact> getCcs() {
        return this.ccs;
    }

    @Override
    public boolean isValid() {
        if (this.getReceivers().size() == 0)
            return false;
        else if (null == this.getText() || "".equals(this.getText()))
            return false;
        else if (null == this.getSender())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Email. From: %s. To: %s",
                this.getSender(),
                this.getReceivers());
    }
}
