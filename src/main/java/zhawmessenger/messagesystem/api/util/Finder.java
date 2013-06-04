package zhawmessenger.messagesystem.api.util;

import java.util.List;
import java.util.Set;

/**
 */
public interface Finder<S, R> {
    Set<R> find(S...findArgs);
}
