package zhawmessenger.ui.impl.components;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;

/**
 */
public class ComponentFactory {
    public static DateTimeChooser buildDatePanel(Date value) {
        return new DateTimeChooser(value);
    }
}
