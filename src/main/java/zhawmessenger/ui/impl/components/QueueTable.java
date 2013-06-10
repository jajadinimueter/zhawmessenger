package zhawmessenger.ui.impl.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 */
public class QueueTable extends JTable {
    private TableCellRenderer renderer;

    public QueueTable(TableModel dm) {
        super(dm);
        renderer = new CellRenderer();
        this.setRowHeight(100);
    }

    class CellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            renderedLabel.setHorizontalAlignment(SwingConstants.LEFT);
            renderedLabel.setVerticalAlignment(SwingConstants.TOP);
            renderedLabel.setBorder(new EmptyBorder(10,10,10,10));
            return renderedLabel;
        }
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return renderer;
    }
}
