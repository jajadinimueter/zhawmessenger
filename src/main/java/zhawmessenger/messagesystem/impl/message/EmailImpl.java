package zhawmessenger.messagesystem.impl.message;

import zhawmessenger.messagesystem.api.message.Email;

import java.util.Date;

/**
 */
public class EmailImpl implements Email {

    @Override
    public long getSendTime() {
        return new Date().getTime();
    }

    @Override
    public String getText() {
        return "TODO: Implement";
    }
}
