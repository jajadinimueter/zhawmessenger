package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 */
public class SendAtPanel extends JPanel {
    private JCheckBox sendImmediately;
    private JCheckBox noReminder;
    private DateTimeChooser sendAtChooser;
    private DateTimeChooser remindDateChooser;

    public SendAtPanel() {
        FormBuilder builder = new FormBuilder(
                this, new Insets(3, 3, 3, 3), false);

        FormBuilderConstraints leftAlign = new FormBuilderConstraints(
                FormBuilderConstraints.Align.LEFT);

        // send at
        FormBuilder sendAtBuilder = new FormBuilder();
        sendImmediately = sendAtBuilder.addField(
                new JCheckBox("Sofort"),
                leftAlign);
        sendAtChooser = sendAtBuilder.addField(
                ComponentFactory.buildDatePanel(new Date()));
        builder.addComponent(new JLabel("Versenden am"), sendAtBuilder.getPanel());

        // reminder
        FormBuilder remindAtBuilder = new FormBuilder();
        noReminder = remindAtBuilder.addField(
                new JCheckBox("Keine Erinnerung"),
                leftAlign);
        remindDateChooser = remindAtBuilder.addField(
                ComponentFactory.buildDatePanel(new Date()));
        builder.addComponent(new JLabel("Erinnerung am"), remindAtBuilder.getPanel());
    }

    public Date getSendDate() {
        return sendAtChooser.getDate();
    }
}
