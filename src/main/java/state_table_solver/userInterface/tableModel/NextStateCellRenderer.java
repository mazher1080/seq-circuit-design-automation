package state_table_solver.userInterface.tableModel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import state_table_solver.stateTable.State;
/**
 * <p>Renders a JComboBox to each cell under next state columns. Displays all user-specified states.
 * 
 * @author Muneeb Azher
 * @author Jacob Head
 */
public class NextStateCellRenderer extends DefaultTableCellRenderer {
    
    /** 
     * Gets the component to render for the state cell.
     * 
     * @param table The current table.
     * @param value The value of the cell.
     * @param isSelected If the cell is selected.
     * @param hasFocus If the cell has focus.
     * @param row The cells row index.
     * @param column The cells column index.
     * @return The component to be rendered in the cell.
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
