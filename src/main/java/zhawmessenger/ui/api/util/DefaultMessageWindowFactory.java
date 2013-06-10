package zhawmessenger.ui.api.util;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.CancelListener;
import zhawmessenger.ui.api.MessageWindowFactory;
import zhawmessenger.ui.api.SaveListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class DefaultMessageWindowFactory implements MessageWindowFactory {
    private static int DEFAULT_WIDTH = 800;
    private static int DEFAULT_HEIGHT = 600;

    private List<SaveListener> saveListeners = new ArrayList<SaveListener>();
    private List<CancelListener> cancelListeners = new ArrayList<CancelListener>();

    private int width;
    private int height;

    public DefaultMessageWindowFactory() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public DefaultMessageWindowFactory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Window createWindow(JFrame owner) {
        return createWindow(owner, width, height);
    }

    @Override
    public Window createWindow(JFrame owner, int width, int height) {
        final JDialog window = new JDialog(owner, "Create", true);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setSize(width, height);

        this.addCancelListener(new CancelListener() {
            @Override
            public void canceled(Message message) {
                window.setVisible(false);
                window.dispose();
            }
        });

        return window;
    }

    @Override
    public void addCancelListener(CancelListener listener) {
        this.cancelListeners.add(listener);
    }

    @Override
    public void addSaveListener(SaveListener listener) {
        this.saveListeners.add(listener);
    }
}
