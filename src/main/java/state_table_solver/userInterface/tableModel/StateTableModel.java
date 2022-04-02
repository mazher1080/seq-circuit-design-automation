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

    /**
     * Class constructor. Creates a new state table with an associated controller.
     * 
     * @param columnLabels The labels for the columns.
     * @param numCols The number of columns in the state table.
     * @param controller The app controller.
     */
    public StateTableModel(String[] columnLabels, int numCols, Controller controller) {
        super();
        this.COLUMN_LABELS = columnLabels;
        this.numCols = numCols;
        this.controller = controller;
    }

    /** 
     * Returns true if a cell at <code>rowIndex</code> and <code>columnIndex</code> is editable. False otherwise.
     * 
     * @param rowIndex The row index to check.
     * @param columnIndex The column index to check.
     * @return True if the cell is editable.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (
            0 <= columnIndex && columnIndex <= getColumnCount() &&
            0 <= rowIndex && rowIndex <= this.getController().appData().getStateTable().getStateCount()
        );
    }

    /** 
     * Obtain the object value of a cell at a current position in the table.
     * 
     * @param rowIndex The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return The object stored in the cell.
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

    /** 
     * Set the object value specified in a cell.
     * 
     * @param aValue The new value to set the cell to.
     * @param rowIndex The row index of the cell.
     * @param columnIndex The column index of the cell.
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

    /** 
     * Deletes a row from the state table.
     * 
     * @param index The index of the row to delete.
     */
    public void deleteRow(int index) {
        getController().appData().getStateTable().removeRow(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Getter for the number of rows in the state table.
     * 
     * @return The number of rows in the state table.
     */
    @Override
    public int getRowCount() {
        return getController().appData().getStateTable().getStateCount();
    }

    /**
     * Getter for the number of columns in the state table.
     * 
     * @return The number of colunmns in the state table.
     */
    @Override
    public int getColumnCount() {
        return this.numCols;
    }

    /** 
     * Gets the label for a column at a given index.
     * 
     * @param columnIndex The index of the column.
     * @return The name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        assert (columnIndex >= 0 && columnIndex < COLUMN_LABELS.length);
        return COLUMN_LABELS[columnIndex];
    }

    /** 
     * Gets the class for a column at a given index.
     * 
     * @param columnIndex The index of the column.
     * @return The class of the column.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /** 
     * Obtain the controller passed into the model.
     * 
     * @return The app controller.
     */
    public Controller getController() {
        return controller;
    }

    /** Setter for the controller.
     * 
     * @param controller The new controller to set.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
}
