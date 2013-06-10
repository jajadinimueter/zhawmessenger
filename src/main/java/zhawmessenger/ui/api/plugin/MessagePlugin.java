package zhawmessenger.ui.api.plugin;

import zhawmessenger.messagesystem.api.message.MessageFactory;
import zhawmessenger.ui.api.ItemFormatter;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.MessageWindowFactory;

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
