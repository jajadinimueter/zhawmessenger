package zhawmessenger.messagesystem.impl.modules.print.transport;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.modules.print.message.PrintJob;
import zhawmessenger.messagesystem.api.modules.print.transport.PrintTransport;
import zhawmessenger.messagesystem.api.transport.SentMessage;
import zhawmessenger.messagesystem.api.transport.TransportException;
import zhawmessenger.messagesystem.api.util.SentMessageLogger;

import java.util.Date;

/**
 */
public class FakePrintTransportImpl implements PrintTransport {
    private SentMessageLogger messageLogger;

    public FakePrintTransportImpl(SentMessageLogger messageLogger) {
        this.messageLogger = messageLogger;
    }

    @Override
    public boolean canSend(Class<? extends Message<?>> messageClass) {
        return PrintJob.class.isAssignableFrom(messageClass);
    }

    @Override
    public SentMessage<PrintJob> send(final PrintJob message) throws TransportException {
        SentMessage<PrintJob> sentMessage = new SentMessage<PrintJob>() {
            @Override
            public PrintJob getMessage() {
                return message;
            }

            @Override
            public Date sentAt() {
                return new Date();
            }
        };
        this.messageLogger.log(sentMessage);
        return sentMessage;
    }
}
