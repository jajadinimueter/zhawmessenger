package zhawmessenger.ui.impl.queue;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueueChangeListener;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.ui.api.ItemFormatter;
import zhawmessenger.ui.api.plugin.MessagePlugin;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public class QueueTableModel extends AbstractTableModel {
    private final MessageQueue messageQueue;
    private final List<QueuedMessage> messages;
    private final List<MessagePlugin> messagePlugins;

    public QueueTableModel(MessageQueue messageQueue, List<MessagePlugin> plugins) {
        this.messageQueue = messageQueue;
        this.messages = new ArrayList<QueuedMessage>();
        addQueueMessages();
        this.messagePlugins = plugins;
        messageQueue.addQueueChangeListener(new QueueChangeListener() {
            @Override
            public void queueChanged(MessageQueue queue) {
                addQueueMessages();
            }
        });
    }

    private void addQueueMessages() {
        messages.clear();
        for (QueuedMessage message : messageQueue.getQueuedMessages()) {
            messages.add(message);
        }
        this.fireTableDataChanged();
    }

    public List<QueuedMessage> getMessages() {
        return messages;
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "#";
        } else if (column == 1) {
            return "Status";
        } else if (column == 2) {
            return "Nachricht";
        }
        throw new RuntimeException("No such column");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QueuedMessage m = messages.get(rowIndex);

        ItemFormatter<Message> formatter = new ItemFormatter<Message>() {
            @Override
            public String format(Message message) {
                return message.getText();
            }
        };

        for (MessagePlugin p : messagePlugins) {
            if (p.doesHandle(m.getMessage().getClass())) {
                ItemFormatter<Message> mf = p.getPreviewFormatter();
                if (mf != null) {
                    formatter = mf;
                }
            }
        }

        if (columnIndex == 0) {
            return m.getMessage().getId();
        } else if ( columnIndex == 1 ) {
            return m.getState();
        } if (columnIndex == 2) {
            return formatter.format(m.getMessage());
        }

        throw new RuntimeException("No such column");
    }
}
