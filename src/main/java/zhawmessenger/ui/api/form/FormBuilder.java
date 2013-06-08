package zhawmessenger.ui.api.form;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class FormBuilder {
    private final JPanel panel;
    private final Insets insets;
    private final JPanel stopper;
    private boolean withStopper = true;
    private int yPosition = 0;

    public FormBuilder(JPanel panel, Insets insets, boolean withStopper) {
        this.withStopper = withStopper;
        this.stopper = new JPanel();
        this.insets = insets;
        this.panel = panel;
        this.panel.setLayout(new GridBagLayout());
    }

    class DefaultGridBagConstraintsChanger extends  GridBagConstraintsChangerAdapter {}

    public FormBuilder(JPanel panel, boolean withStopper) {
        this(panel, new Insets(0, 0, 0, 0), withStopper);
    }

    public FormBuilder() {
        this(new JPanel(), true);
    }


    public <T extends Component> T addField(T component) {
        return addField(component, null, null);
    }

    public <T extends Component> T addField(T component,
                                            GridBagConstraintsChanger gbcChanger,
                                            FormBuilderConstraints fbc) {
        return addComponent(null, component, gbcChanger, fbc);
    }

    public <T extends Component> T addField(T component,
                                            GridBagConstraintsChanger gbcChanger) {
        return addComponent(null, component, gbcChanger, null);
    }

    public <T extends Component> T addField(T component,
                                            FormBuilderConstraints fbc) {
        return addComponent(null, component, null, fbc);
    }

    public <T extends Component> T addComponent(JLabel label,
                                                T component) {
        return addComponent(label, component, null, null);
    }

    public <T extends Component> T addComponent(JLabel label,
                                                T component,
                                                GridBagConstraintsChanger gbcChanger) {
        return addComponent(label, component, gbcChanger, null);

    }

    public <T extends Component> T addComponent(JLabel label,
                                                T component,
                                                GridBagConstraintsChanger gbcChanger,
                                                FormBuilderConstraints fbc) {
        List<T> compList = new ArrayList<T>();
        compList.add(component);
        //noinspection unchecked
        return addComponent(label, compList, gbcChanger, fbc).get(0);
    }

    /**
     * Adds a standard field/label row to the panel
     *
     * @param label the label, may be null
     * @param components the component, may be null
     */
    public <T extends Component> List<T> addComponent(JLabel label,
                                                      List<T> components,
                                                      GridBagConstraintsChanger gbcChanger,
                                                      FormBuilderConstraints fbc) {

        if (fbc == null) {
            fbc = new FormBuilderConstraints();
        }

        if (gbcChanger == null) {
            gbcChanger = new DefaultGridBagConstraintsChanger();
        }

        // remove last stopper. we add a new one afterwards
        this.removeStopper();

        if (label != null) {
            GridBagConstraints labelGbc = createLabelConstraints();
            GridBagConstraints spacerGbc = createHorizontalSpacerConstraints();
            labelGbc.gridy = yPosition;
            labelGbc.gridx = 0;
            if (components == null || components.size() == 0) {
                if (fbc.align == null) {
                    labelGbc.gridwidth = 2;
                } else {
                    labelGbc.weightx = 0.0;
                    if (fbc.align == FormBuilderConstraints.Align.RIGHT) {
                        spacerGbc.weightx = 1.0;
                        this.panel.add(new JPanel(), spacerGbc);
                    }
                }
            }

            labelGbc = gbcChanger.changeLabel(label, labelGbc);

            this.panel.add(label, labelGbc);

            if (components == null || components.size() == 0) {
                if (fbc.align == FormBuilderConstraints.Align.LEFT) {
                    spacerGbc.weightx = 1.0;
                    labelGbc.weightx = 0.0;
                    this.panel.add(new JPanel(), spacerGbc);
                }
            }
        }

        if (components != null) {
            JPanel fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new GridBagLayout());

            GridBagConstraints panelSpacerGbc = createHorizontalSpacerConstraints();

            GridBagConstraints fieldPanelGbc = createComponentConstraints();

            GridBagConstraints fieldGbc = createGridBagConstraints();

            // when label is null, use whole space
            if (label == null) {
                fieldPanelGbc.gridx = 0;
                fieldPanelGbc.gridwidth = 2;
            } else {
                fieldPanelGbc.gridx = 1;
            }

            fieldGbc.gridx = 0;
            fieldGbc.gridy = 0;
            fieldGbc.weighty = 1.0;

            if (fbc.align == null) {
                fieldGbc.weightx = 1.0 / components.size();
            } else {
                fieldGbc.weightx = 0.0;
            }

            fieldGbc.fill = GridBagConstraints.HORIZONTAL;

            for (T comp : components) {
                fieldGbc = gbcChanger.changeField(comp, fieldGbc);
                fieldsPanel.add(comp, fieldGbc);
                fieldGbc.gridx += 1;
            }

            fieldPanelGbc.gridy = yPosition;
            fieldPanelGbc.weightx = 1.0;
            fieldPanelGbc.fill = GridBagConstraints.HORIZONTAL;

            fieldPanelGbc = gbcChanger.changeFieldContainer(
                    fieldsPanel, fieldPanelGbc);

            if (fbc.align == FormBuilderConstraints.Align.LEFT) {
                panelSpacerGbc.weightx = 1.0;
                fieldsPanel.add(new JPanel(), panelSpacerGbc);
            }

            panel.add(fieldsPanel, fieldPanelGbc);
        }

        yPosition += 1;

        // add the stopper to "press" components up
        this.addStopper();

        // just for easy adding
        return components;
    }

    protected GridBagConstraints addGbcDefaults(GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.insets = insets;
        return gbc;
    }

    protected GridBagConstraints createComponentConstraints() {
        GridBagConstraints gbc = addGbcDefaults(new GridBagConstraints());
        gbc.weightx = .9;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    protected GridBagConstraints createLabelConstraints() {
        GridBagConstraints gbc = addGbcDefaults(new GridBagConstraints());
        gbc.weightx = .1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    protected GridBagConstraints createHorizontalSpacerConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        return gbc;
    }

    public GridBagConstraints createGridBagConstraints() {
        return addGbcDefaults(new GridBagConstraints());
    }

    /**
     * Remove the stopper at the end because we want to add
     * another component. The stopper will be readded afterwards
     */
    private void removeStopper() {
        if ( withStopper ) {
            if (Arrays.asList(panel.getComponents()).contains(stopper)) {
                panel.remove(stopper);
            }
        }
    }

    /**
     * Add a panel at the end of the form to press the
     * other components upwards
     */
    private void addStopper() {
        if ( withStopper ) {
            GridBagConstraints gbc = createGridBagConstraints();
            gbc.weighty = 1.0;
            gbc.gridx = 0;
            gbc.gridy = yPosition;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            yPosition += 1;
            this.panel.add(stopper, gbc);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
