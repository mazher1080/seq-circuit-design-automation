package state_table_solver.userInterface;

import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.State;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * StateTableModel is a class used to construct a JTable based on the
 * necessary methods of a state table.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public abstract class StateTableModel extends AbstractTableModel {
    private int numCols;
    private int numRows = 2;
    private final String[] COLUMN_LABELS;

    public StateTableModel(String[] columnLabels, int numCols) {
        super();
        this.COLUMN_LABELS = columnLabels;
        this.numCols = numCols;
    }

    @Override
    public int getRowCount() {
        return this.numRows;
    }

    @Override
    public int getColumnCount() {
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

    public abstract void setValueAt(Object aValue, int rowIndex, int columnIndex);

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }
    
}
