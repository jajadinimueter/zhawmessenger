package zhawmessenger.ui.api.form.validator;

import zhawmessenger.ui.api.Constants;
import zhawmessenger.ui.api.validator.Validator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 */
public class ComboBoxRequiredValidator implements Validator {
    private JComboBox cbox;
    private Border border;

    public ComboBoxRequiredValidator(JComboBox cbox) {
        this.cbox = cbox;
        this.border = cbox.getBorder();
    }

    @Override
    public boolean validate() {
        if (cbox.getSelectedItem() == null) {
            cbox.setBorder(Constants.ERROR_BORDER);
            return false;
        } else {
            cbox.setBorder(border);
            return true;
        }
    }
}
