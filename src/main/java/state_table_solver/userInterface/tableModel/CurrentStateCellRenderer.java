package state_table_solver.userInterface.tableModel;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import state_table_solver.stateTable.State;

/**
 * <p>Renders a cell under current state column. Displays all user-specified states.
 * @author Jacob Head
 */

public class CurrentStateCellRenderer extends DefaultTableCellRenderer {
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
            if (value instanceof State) {
                State state = (State) value;
                setText(state.getId());
            }

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getSelectionForeground());
            }
        return this;
    }
}