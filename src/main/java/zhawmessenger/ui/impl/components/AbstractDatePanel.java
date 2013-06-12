package zhawmessenger.ui.impl.components;

import javax.swing.*;
import java.util.Date;

/**
 */
public abstract class AbstractDatePanel extends JPanel{
    public abstract Date getDate();

    public abstract void setDate(Date date);
}

