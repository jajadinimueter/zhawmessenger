package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.api.formbuilder.FormBuilderConstraints;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
                new JCheckBox("Sofort", true),
                leftAlign);

        sendAtChooser = sendAtBuilder.addField(
                ComponentFactory.buildDatePanel(null));

        sendImmediately.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeEnabled();
            }
        });

        changeEnabled();

        builder.addComponent(new JLabel("Versenden am"), sendAtBuilder.getPanel());

        this.setBorder(new LineBorder(Color.LIGHT_GRAY));
    }

    private void changeEnabled() {
        for (Component c : sendAtChooser.getComponents()) {
            c.setEnabled(!sendImmediately.isSelected());
        }
    }

    public void setDate(Date date) {
        if ( date != null)
            sendImmediately.setSelected(false);

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
