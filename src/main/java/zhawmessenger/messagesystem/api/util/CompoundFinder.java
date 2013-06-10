package zhawmessenger.messagesystem.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class CompoundFinder<S, T> implements Finder<S, T> {
    private Collection<Finder<S,T>> finders;

    public CompoundFinder() {
        this(new ArrayList<Finder<S, T>>());
    }

    public CompoundFinder(Collection<Finder<S, T>> finders) {
        this.finders = finders;
    }

    void addFinder(Finder<S, T> finder) {
        finders.add(finder);
    }

    void removeFinder(Finder<S, T> finder) {
        finders.remove(finder);
    }

    @Override
    public Set<T> find(S... findArgs) {
        Set<T> found = new HashSet<T>();
        for (Finder<S, T> finder : finders) {
            found.addAll(finder.find(findArgs));
        }
        return found;
    }

}
