package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.MessageFactory;

import javax.swing.*;

/**
 */
public interface MessagePlugin<T> {

    boolean doesHandle(Class<T> messageClass);

    Class<T> getMessageClass();

    String getName();

    Icon getIcon();

    ItemFormatter<T> getPreviewFormatter();

    MessageFactory getMessageFactory();

    MessageWindowFactory getWindowFactory();

    MessageFormFactory getFormFactory();

}
