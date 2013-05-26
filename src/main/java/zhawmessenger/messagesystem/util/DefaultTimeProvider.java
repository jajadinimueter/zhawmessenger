package zhawmessenger.messagesystem.util;

import java.util.Date;

/**
 */
public class DefaultTimeProvider implements TimeProvider {
    @Override
    public long getTime() {
        return new Date().getTime();
    }
}
