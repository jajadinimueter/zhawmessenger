package zhawmessenger.ui;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.Constants;
import zhawmessenger.ui.api.ItemFormatter;

/**
 */
public class SimpleMessageFormatter<T extends Message> implements ItemFormatter<T> {
    private String type;
    private String before;

    public SimpleMessageFormatter(String type) {
        this.type = type;
    }

    @Override
    public String format(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<b>");
        builder.append(type);
        builder.append("</b><br>");
        if (message.getSendDate() != null) {
            builder.append("<b>Versand: </b><br>");
            builder.append(Constants.DATE_FORMAT.format(message.getSendDate()));
        }
        builder.append(message.getText());
        builder.append("</html>");
        return builder.toString();
    }
}
