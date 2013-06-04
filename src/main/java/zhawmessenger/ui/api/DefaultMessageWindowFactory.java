package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class DefaultMessageWindowFactory implements MessageWindowFactory {
    private List<SaveListener> saveListeners = new ArrayList<SaveListener>();
    private List<CancelListener> cancelListeners = new ArrayList<CancelListener>();

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
