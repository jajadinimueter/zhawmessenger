package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.form.MessageFormFactory;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class AbstractFormFactory<M extends Message>
        implements MessageFormFactory<M> {
}
