package zhawmessenger.messagesystem.impl.persistance;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;
import zhawmessenger.messagesystem.api.util.Finder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class MemoryContactFinder
        implements Finder<String, DisplayableContactProvider> {

    private final List<SearchableRepository<? extends DisplayableContactProvider>> repositories;

    public MemoryContactFinder() {
        this(new ArrayList<SearchableRepository<? extends DisplayableContactProvider>>());
    }

    public MemoryContactFinder(List<SearchableRepository<? extends DisplayableContactProvider>> repositories) {
        this.repositories = repositories;
    }

    public void addRepository(SearchableRepository<DisplayableContactProvider> repo) {
        this.repositories.add(repo);
    }

    @Override
    public Set<DisplayableContactProvider> find(String... findArgs) {
        Set<DisplayableContactProvider> contacts = new HashSet<DisplayableContactProvider>();
        for (SearchableRepository<? extends DisplayableContactProvider> sr : repositories) {
            for (String arg : findArgs) {
                contacts.addAll(sr.find(arg, true));
            }
        }
        return contacts;
    }
}
