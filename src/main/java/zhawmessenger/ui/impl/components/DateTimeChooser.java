package zhawmessenger.ui.impl.components;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class DateTimeChooser extends JPanel {
    private JDateChooser dateChooser;
    private SpinnerDateModel spinnerModel;
    private JSpinner timeSpinner;

    public DateTimeChooser(Date value){
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.weightx = 1.0;

        dateChooser = new JDateChooser();
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
        this.add(dateChooser, gbc);

        spinnerModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(spinnerModel);
        JComponent editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);
        if(value != null) {
            timeSpinner.setValue(value);
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(timeSpinner, gbc);
    }

    public void setDate(Date date) {
        if ( date != null ) {
            dateChooser.setDate(date);
            timeSpinner.setValue(date);
        }
    }

    public Date getDate() {
        if (dateChooser.getDate() == null
                || spinnerModel.getDate() == null) {
            return null;
        }

        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(spinnerModel.getDate());

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.dateChooser.getDate());
        cal.set(Calendar.HOUR, timeCal.get(Calendar.HOUR));
        cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

        return cal.getTime();
    }
}
