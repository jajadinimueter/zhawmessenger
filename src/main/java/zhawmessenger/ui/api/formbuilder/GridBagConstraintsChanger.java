package zhawmessenger.ui.api.formbuilder;

import javax.swing.*;
import java.awt.*;

public interface GridBagConstraintsChanger {
    GridBagConstraints changeField(Component field,
                                   GridBagConstraints gbc);

    GridBagConstraints changeLabel(JLabel label,
                                   GridBagConstraints gbc);

    GridBagConstraints changeFieldContainer(JPanel fieldContainer,
                                            GridBagConstraints gbc);
}