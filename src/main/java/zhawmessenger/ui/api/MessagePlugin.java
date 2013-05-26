package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;

/**
 */
public interface MessagePlugin<T extends Message> {

    boolean doesHandle(Class<? extends Message> messageClass);

    Class<? extends Message> getMessageClass();

    String getName();

    Icon getIcon();

    MessageFormFactory getFormFactory();

}
