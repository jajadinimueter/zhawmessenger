package zhawmessenger.messagesystem.api.modules.auth;

/**
 */
public interface Authenticator {
    Principal authenticate(Credentials credentials);
}
