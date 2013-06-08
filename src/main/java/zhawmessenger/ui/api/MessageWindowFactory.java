package zhawmessenger.ui.api;

import javax.swing.*;
import java.awt.*;

/**
 */
public interface MessageWindowFactory extends SaveObservable, CancelObservable {
    Window createWindow(JFrame owner);

    Window createWindow(JFrame owner, int width, int height);
}
