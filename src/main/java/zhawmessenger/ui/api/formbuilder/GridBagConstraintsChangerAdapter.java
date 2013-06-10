package zhawmessenger.ui.api.formbuilder;

import javax.swing.*;
import java.awt.*;

public abstract class GridBagConstraintsChangerAdapter implements GridBagConstraintsChanger {

    @Override
    public GridBagConstraints changeField(Component field,
                                          GridBagConstraints gbc) {
        return gbc;
    }

    @Override
    public GridBagConstraints changeLabel(JLabel label,
                                          GridBagConstraints gbc) {
        return gbc;
    }

    @Override
    public GridBagConstraints changeFieldContainer(JPanel fieldContainer,
                                                   GridBagConstraints gbc) {
        return gbc;
    }
}
