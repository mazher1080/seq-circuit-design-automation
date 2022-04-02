package state_table_solver.userInterface.tableModel;

import state_table_solver.Controller;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.State;

/**
 * Responsible for the table model of a Mealy state machine project
 * @author Muneeb Azher
 */
public class MealyTableModel extends StateTableModel {
    public MealyTableModel(String[] columnLabels, int numCols, Controller controller) {
        super(columnLabels, numCols, controller);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0 || columnIndex == 1 || columnIndex == 2)
            return State.class;
        if(columnIndex == 3 || columnIndex == 4)
            return Bit.class;
        return null;
    }
    
}
