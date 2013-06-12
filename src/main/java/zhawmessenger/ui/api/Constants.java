package zhawmessenger.ui.api;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 */
public interface Constants {

    public static Border ERROR_BORDER = new LineBorder(Color.RED, 5);

    public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy");

}
