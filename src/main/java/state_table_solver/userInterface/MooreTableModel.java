package state_table_solver.userInterface;

import state_table_solver.AppData;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.State;

/**
 * Responsible for the table model of a Moore state machine project
 * @author Muneeb Azher
 */
public class MooreTableModel extends StateTableModel {
    public MooreTableModel(String[] columnLabels, int numCols, AppData appData) {
        super(columnLabels, numCols, appData);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        fireTableCellUpdated(rowIndex, columnIndex);
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        State s = null;
        Bit b = null;
        if(columnIndex >= 0 && columnIndex <= 2) {
            if (columnIndex == 0) {
                s = this.getAppData().getStateTable().getCurrentStateCol().get(rowIndex);
            } else if (columnIndex == 1) {
                s = this.getAppData().getStateTable().getNextLowStateCol().get(rowIndex);
            } else {
                s = this.getAppData().getStateTable().getNextHighStateCol().get(rowIndex);
            }
            return s;
        }
        if(columnIndex == 3) {
            b = this.getAppData().getStateTable().getNextHighOutputCol().get(rowIndex);
            return b;
        }
        else {
            return null;
        }
    }
    
}
