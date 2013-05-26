package zhawmessenger.ui.impl;

import ca.odell.glazedlists.gui.TableFormat;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;

/**
 */
public class MessageTableFormat implements TableFormat<QueuedMessage>{

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "Text";
        }

        throw new IllegalStateException();
    }

    @Override
    public Object getColumnValue(QueuedMessage queuedMessage, int i) {
        switch (i) {
            case 0:
                return queuedMessage.getMessage().getText();
        }

        throw new IllegalStateException();
    }
}
