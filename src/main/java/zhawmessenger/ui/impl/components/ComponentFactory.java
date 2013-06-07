package zhawmessenger.ui.impl.components;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;

/**
 */
public class ComponentFactory {
    public static JPanel buildDatePanel(Date value) {
        JPanel datePanel = new JPanel();
//        datePanel.setBorder(new LineBorder(Color.black));
        datePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.weightx = 1.0;

        JDateChooser dateChooser = new JDateChooser();
        if (value != null) {
            dateChooser.setDate(value);
        }
        for (Component comp : dateChooser.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setColumns(50);
                ((JTextField) comp).setEditable(false);
            }
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        datePanel.add(dateChooser, gbc);

        SpinnerModel model = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(model);
        JComponent editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);
        if(value != null) {
            timeSpinner.setValue(value);
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        datePanel.add(timeSpinner, gbc);

        return datePanel;
    }
}
