package state_table_solver.userInterface.tableModel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import state_table_solver.booleanLogic.Bit;

/**
 * <p>Renders a JComboBox to each cell under output column(s). Displays a set of
 * predefined bits.
 * 
 * @author Jacob Head
 */
public class OutputCellRenderer extends DefaultTableCellRenderer {
    
    /** 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return Component
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Bit) {
                Bit outputBit = (Bit) value;
                setText(outputBit.getId());
            }

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getSelectionForeground());
            }
        return this;
    }
}
