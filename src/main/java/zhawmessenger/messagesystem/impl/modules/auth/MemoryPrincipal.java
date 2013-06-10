package zhawmessenger.messagesystem.impl.modules.auth;

import zhawmessenger.messagesystem.api.modules.auth.Principal;

/**
 */
public class MemoryPrincipal implements Principal {

    private String username;

    public MemoryPrincipal(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
