package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.email.message.Email;

import javax.swing.*;

/**
 */
public interface MessagePlugin{

    boolean doesHandle(Class<? extends Message> messageClass);

    Class<? extends Message> getMessageClass();

    String getName();

    Icon getIcon();

    MessageWindowFactory getWindowFactory();

    MessageFormFactory getFormFactory();

}
