package zhawmessenger.ui.impl.queue;

import zhawmessenger.messagesystem.api.queue.QueuedMessage;

import java.util.Comparator;

/**
 */
public class MessageComparator implements Comparator<QueuedMessage>{

    @Override
    public int compare(QueuedMessage o1, QueuedMessage o2) {
        if (o1.getMessage().getSendTime() <
                o2.getMessage().getSendTime()) {
            return -1;
        } else if (o1.getMessage().getSendTime() ==
                o2.getMessage().getSendTime()) {
            return 0;
        } else {
            return 1;
        }

    }
}
