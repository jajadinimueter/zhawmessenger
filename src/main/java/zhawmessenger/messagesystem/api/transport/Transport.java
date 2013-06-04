package zhawmessenger.messagesystem.api.transport;

import zhawmessenger.messagesystem.api.message.Message;

/**
 */
public interface Transport {

    /**
     * Test whether this transport is able to
     * send class `cls`
     *
     * @param messageClass the interface class to check for
     */
    boolean canSend(Class<? extends Message> messageClass);

    /**
     * Send a message over the transport. This must
     * return a {@link SentMessage} instance or
     * throw a {@link TransportException}.
     *
     * @param message The message to send over the
     *                transport
     *
     * @return a {@link SentMessage} instance which
     *         contains information about the progress
     *         of the sent message.
     */
    <T extends Message> SentMessage<T> send(T message) throws TransportException;
}
