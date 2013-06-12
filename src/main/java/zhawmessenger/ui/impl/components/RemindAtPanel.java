package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 */
public class RemindAtPanel extends JPanel {
    private JCheckBox noReminder;
    private DateTimeChooser remindDateChooser;

    public RemindAtPanel() {
        FormBuilder builder = new FormBuilder(
                this, new Insets(3, 3, 3, 3), false);

        FormBuilderConstraints leftAlign = new FormBuilderConstraints(
                FormBuilderConstraints.Align.LEFT);

        // reminder
        FormBuilder remindAtBuilder = new FormBuilder();
        noReminder = remindAtBuilder.addField(
                new JCheckBox("Keine Erinnerung"),
                leftAlign);

        remindDateChooser = remindAtBuilder.addField(
                ComponentFactory.buildDatePanel(new Date()));
        builder.addComponent(new JLabel("Erinnerung am"), remindAtBuilder.getPanel());
    }

    public void setDate(Date date) {
        remindDateChooser.setDate(date);
    }

    public Date getDate() {
        if (noReminder.isSelected()) {
            return null;
        } else {
            return remindDateChooser.getDate();
        }
    }
}
