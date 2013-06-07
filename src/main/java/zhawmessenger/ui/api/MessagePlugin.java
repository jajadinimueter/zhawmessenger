package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.messagesystem.api.modules.email.message.Email;

import javax.swing.*;

/**
 */
public interface MessagePlugin<T> {

    boolean doesHandle(Class<T> messageClass);

    Class<T> getMessageClass();

    String getName();

    Icon getIcon();

    MessageFactory getMessageFactory();

    MessageWindowFactory getWindowFactory();

    MessageFormFactory getFormFactory();

}
