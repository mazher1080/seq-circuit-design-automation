package state_table_solver.userInterface;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import state_table_solver.AppData;

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
    private AppData appData;

    public StateTableModel(String[] columnLabels, int numCols, AppData appData) {
        super();
        this.COLUMN_LABELS = columnLabels;
        this.numCols = numCols;
        this.appData = appData;
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

    public abstract Object getValueAt(int rowIndex, int columnIndex);

    public abstract void setValueAt(Object aValue, int rowIndex, int columnIndex);

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
        
    }

    public AppData getAppData() {
        return appData;
    }

    public void setAppData(AppData appData) {
        this.appData = appData;
    }
    
}
