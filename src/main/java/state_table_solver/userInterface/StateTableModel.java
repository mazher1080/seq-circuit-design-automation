package state_table_solver.userInterface;

import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.State;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class StateTableModel implements TableModel {
    private int numCols = 4;
    private int numRows = 2;
    private static final String[] COLUMN_LABELS = {"State", "Next State x = 0", "Next State x = 1", "Output"};

    @Override
    public int getRowCount() {
        return this.numRows;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.numCols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        assert (columnIndex >= 0 && columnIndex < COLUMN_LABELS.length);
        return COLUMN_LABELS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return 0 <= columnIndex && columnIndex < this.numCols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex >= 0 && columnIndex <= 2)
            return State.class;
        if(columnIndex == 3) 
            return Bit.class;
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }
    
}
