package zhawmessenger.ui.impl.components;

import zhawmessenger.ui.api.form.GridBagConstraintsChangerAdapter;

import javax.swing.*;
import java.awt.*;

/**
 */
public class StopperGridBagConstraintsChanger extends GridBagConstraintsChangerAdapter {
    @Override
    public GridBagConstraints changeField(Component field, GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        return gbc;
    }

    @Override
    public GridBagConstraints changeFieldContainer(JPanel fieldContainer,
                                                   GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        return gbc;
    }

}
