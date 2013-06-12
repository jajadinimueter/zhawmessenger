package zhawmessenger.ui.api.form.validator;

import zhawmessenger.ui.api.Constants;
import zhawmessenger.ui.api.validator.Validator;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 */
public class TextFieldRequiredValidator implements Validator {
    private JTextComponent component;
    private Border originalBorder;

    public TextFieldRequiredValidator(JTextComponent component) {
        this.component = component;
        this.originalBorder = component.getBorder();
    }

    @Override
    public boolean validate() {
        if (this.component.getText().trim().equals("")) {
            this.component.setBorder(Constants.ERROR_BORDER);
            return false;
        } else {
            this.component.setBorder(originalBorder);
        }
        return true;
    }
}
