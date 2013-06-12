package zhawmessenger.ui.impl.validator;

import zhawmessenger.ui.api.Constants;
import zhawmessenger.ui.api.validator.Validator;
import zhawmessenger.ui.impl.components.AbstractDatePanel;
import zhawmessenger.ui.impl.components.SendAtPanel;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.validation.ValidatorHandler;
import java.awt.*;

/**
 */
public class DatePanelValidator implements Validator {
    private AbstractDatePanel panel;
    private Border border;

    public DatePanelValidator(AbstractDatePanel panel) {
        this.panel = panel;
    }

    @Override
    public boolean validate() {
        if ( panel.getDate() == null ) {
            panel.setBorder(Constants.ERROR_BORDER);
            return false;
        } else {
            panel.setBorder(border);
            return true;
        }
    }
}
