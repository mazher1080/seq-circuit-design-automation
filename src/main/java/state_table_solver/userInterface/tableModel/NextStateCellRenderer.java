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
