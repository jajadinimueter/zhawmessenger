package zhawmessenger.ui.impl.modules.mobilephone;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.mobilephone.message.ShortMessage;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;
import zhawmessenger.ui.api.ItemFormatter;
import zhawmessenger.ui.api.plugin.MessagePlugin;

import javax.swing.*;
import java.util.List;

/**
 */
public abstract class ShortMessagePlugin<T extends ShortMessage> implements MessagePlugin<T> {

    protected List<SearchableRepository<? extends DisplayableContactProvider>> repos;

    protected ShortMessagePlugin(List<SearchableRepository<? extends DisplayableContactProvider>> repos) {
        this.repos = repos;
    }

    @Override
    public boolean doesHandle(Class<T> messageClass) {
        return getMessageClass().isAssignableFrom(messageClass);
    }

    @Override
    public Icon getIcon() {
        return null;  // FIXME
    }

    @Override
    public ItemFormatter<T> getPreviewFormatter() {
        return new ItemFormatter<T>() {
            @Override
            public String format(T message) {
                return "<html>" +
                        "<b>" + getName() + "</b><br>"
                        + message.getText() +
                        "</html>";
            }
        };
    }

}
