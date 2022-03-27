package state_table_solver.userInterface;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import state_table_solver.stateTable.State;
/**
 * Renders a JComboBox to each cell under next state columns. Displays all user-specified states.
 * @author Muneeb Azher
 */
public class NextStateCellRenderer extends JComboBox<State> implements TableCellRenderer {

    public NextStateCellRenderer(ArrayList<State> nextStateList) {
        super(nextStateList.toArray(new State[nextStateList.size()]));
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getSelectionForeground());
            }
        return this;
    }
}
