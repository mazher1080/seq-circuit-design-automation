package state_table_solver.userInterface.tableModel;

import javax.swing.table.AbstractTableModel;
import state_table_solver.Controller;
import state_table_solver.booleanLogic.Bit;
import state_table_solver.stateTable.State;

/**
 * <p>StateTableModel is a class used to construct a JTable based on the
 * necessary methods of a state table.
 * 
 * @author Jacob Head
 * @author Muneeb Azher
 */

public class StateTableModel extends AbstractTableModel {
    private int numCols;
    private final String[] COLUMN_LABELS;
    private Controller controller;

    public StateTableModel(String[] columnLabels, int numCols, Controller controller) {
        super();
        this.COLUMN_LABELS = columnLabels;
        this.numCols = numCols;
        this.controller = controller;
    }

    /** Returns true if a cell at <code>rowIndex</code> and <code>columnIndex</code> is editable. False otherwise.
     * @param rowIndex
     * @param columnIndex
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (
            0 <= columnIndex && columnIndex <= getColumnCount() &&
            0 <= rowIndex && rowIndex <= this.getController().appData().getStateTable().getStateCount()
        );
    }

    /** Obtain the object value specified in a cell
     * @param rowIndex
     * @param columnIndex
     * @return Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        State s = null;
        Bit b = null;
        if(columnIndex >= 0 && columnIndex <= 2) {
            if (columnIndex == 0) {
                s = this.getController().appData().getStateTable().getCurrentStateCol().get(rowIndex);
            } else if (columnIndex == 1) {
                s = this.getController().appData().getStateTable().getNextLowStateCol().get(rowIndex);
            } else {
                s = this.getController().appData().getStateTable().getNextHighStateCol().get(rowIndex);
            }
            return s;
        } else if(columnIndex == 3) {
            b = this.getController().appData().getStateTable().getNextLowOutputCol().get(rowIndex);
            return b;
        } else if(columnIndex == 4 && columnIndex < getColumnCount()) {
            b = this.getController().appData().getStateTable().getNextHighOutputCol().get(rowIndex);
            return b;
        } else {
            return null;
        }
    }

    /** Set the object value specified in a cell
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex >= 0 && columnIndex <= 2) {
            State s = (State) aValue;
            if (columnIndex == 0)
                this.getController().appData().getStateTable().getCurrentStateCol().set(rowIndex, s);
            else if (columnIndex == 1)
                this.getController().appData().getStateTable().getNextLowStateCol().set(rowIndex, s);
            else
                this.getController().appData().getStateTable().getNextHighStateCol().set(rowIndex, s);
        }
        if(columnIndex == 3) {
            Bit b = (Bit) aValue;
            this.getController().appData().getStateTable().getNextLowOutputCol().set(rowIndex, b);
        } else if(columnIndex == 4 && columnIndex < getColumnCount()) {
            Bit b = (Bit) aValue;
            this.getController().appData().getStateTable().getNextHighOutputCol().set(rowIndex, b);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /** Deletes a row from the state table
     * @param index
     */
    public void deleteRow(int index) {
        getController().appData().getStateTable().removeRow(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * @return int
     */
    @Override
    public int getRowCount() {
        return getController().appData().getStateTable().getStateCount();
    }

    /**
     * @return int
     */
    @Override
    public int getColumnCount() {
        return this.numCols;
    }

    /** 
     * @param columnIndex
     * @return String
     */
    @Override
    public String getColumnName(int columnIndex) {
        assert (columnIndex >= 0 && columnIndex < COLUMN_LABELS.length);
        return COLUMN_LABELS[columnIndex];
    }

    /** 
     * @param columnIndex
     * @return Class<?>
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /** Obtain the controller value passed into the model
     * @return Controller
     */
    public Controller getController() {
        return controller;
    }

    /** Set the controller value corresponding to the model
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
}
