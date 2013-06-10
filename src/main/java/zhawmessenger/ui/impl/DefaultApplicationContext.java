package zhawmessenger.ui.impl;

import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.ui.api.ApplicationContext;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 */
public class DefaultApplicationContext implements ApplicationContext {

    private static DefaultApplicationContext instance;
    private static Lock lock = new ReentrantLock();

    private Person userLoggedIn;

    private DefaultApplicationContext() {
    }

    public void setUserLoggedIn(Person person) {
        this.userLoggedIn = person;
    }

    @Override
    public Person getUserLoggedIn() {
        return userLoggedIn;
    }

    public static DefaultApplicationContext getInstance() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new DefaultApplicationContext();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }
}
