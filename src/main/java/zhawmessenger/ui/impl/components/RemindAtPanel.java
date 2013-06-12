package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Date;

/**
 */
public class RemindAtPanel extends AbstractDatePanel {
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
                new JCheckBox("Keine Erinnerung", true),
                leftAlign);

        remindDateChooser = remindAtBuilder.addField(
                ComponentFactory.buildDatePanel(null));

        noReminder.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeEnabled();
            }
        });

        changeEnabled();

        builder.addComponent(new JLabel("Erinnerung am"), remindAtBuilder.getPanel());
    }

    private void changeEnabled() {
        for (Component c : remindDateChooser.getComponents()) {
            c.setEnabled(!noReminder.isSelected());
        }
    }

    public void setDate(Date date) {
        if ( date != null)
            noReminder.setSelected(false);
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
