package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.ui.api.action.AbstractCreateAction;

import javax.swing.*;

/**
 */
public interface MessagePlugin<T extends Message> {

    boolean doesHandle(Class<? extends Message> messageClass);

    String getName();

    Icon getIcon();

    MessageFormFactory getFormFactory();

}
