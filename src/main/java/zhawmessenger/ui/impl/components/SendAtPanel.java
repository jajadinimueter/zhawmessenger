package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;

/**
 */
public class SendAtPanel extends AbstractDatePanel {
    private JCheckBox sendImmediately;
    private DateTimeChooser sendAtChooser;

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

        this.setBorder(new LineBorder(Color.LIGHT_GRAY));
    }

    public void setDate(Date date) {
        sendAtChooser.setDate(date);
    }

    public Date getDate() {
        if (sendImmediately.isSelected()) {
            return null;
        } else {
            return sendAtChooser.getDate();
        }
    }
}
