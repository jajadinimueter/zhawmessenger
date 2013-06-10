package zhawmessenger.ui.impl.queue;

import ca.odell.glazedlists.gui.TableFormat;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.ui.api.ItemFormatter;
import zhawmessenger.ui.api.plugin.MessagePlugin;

import java.util.List;

/**
 */
public class MessageTableFormat implements TableFormat<QueuedMessage>{

    private List<MessagePlugin> plugins;

    public MessageTableFormat(List<MessagePlugin> plugins) {
        this.plugins = plugins;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "ID";
            case 1: return "Text";
        }

        throw new IllegalStateException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getColumnValue(QueuedMessage queuedMessage, int i) {
        ItemFormatter<Message> formatter = new ItemFormatter<Message>() {
            @Override
            public String format(Message message) {
                return message.getText();
            }
        };

        for (MessagePlugin p : plugins) {
            if (p.doesHandle(queuedMessage.getMessage().getClass())) {
                ItemFormatter<Message> mf = p.getPreviewFormatter();
                if (mf != null) {
                    formatter = mf;
                }
            }
        }

        switch (i) {
            case 0:
                return queuedMessage.getMessage().getId();
            case 1:
                return formatter.format(queuedMessage.getMessage());
        }

        throw new IllegalStateException();
    }
}
