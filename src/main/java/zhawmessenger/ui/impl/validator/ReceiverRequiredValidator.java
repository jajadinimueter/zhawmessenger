package zhawmessenger.ui.impl.validator;

import zhawmessenger.ui.api.Constants;
import zhawmessenger.ui.api.validator.Validator;
import zhawmessenger.ui.impl.components.ReceiverTextArea;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 */
public class ReceiverRequiredValidator implements Validator {
    private ReceiverTextArea field;
    private Border border;

    public ReceiverRequiredValidator(ReceiverTextArea field) {
        this.field = field;
        this.border = field.getBorder();
    }

    @Override
    public boolean validate() {
        if (field.getContactProviders().size() == 0) {
            field.setBorder(Constants.ERROR_BORDER);
            return false;
        } else {
            field.setBorder(border);
            return true;
        }
    }
}
