package state_table_solver.userInterface;

import javax.swing.table.TableColumn;

import state_table_solver.Controller;
import state_table_solver.userInterface.tableModel.OutputCellEditor;
import state_table_solver.userInterface.tableModel.OutputCellRenderer;
import state_table_solver.userInterface.tableModel.StateTableModel;

/**
 * Used to create the User Interface for a Mealy state machine project
 * 
 * @author Muneeb Azher
 * @author Jacob Head
 */
public class MealyTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"Current State", "Next state x = 0", "Next state x = 1", "Output x = 0", "Output x = 1"};
    private static final int NUM_COLS = 5;
    private static final int TABLE_WIDTH = 700;
    private StateTableModel model = new StateTableModel(COLUMN_LABELS, NUM_COLS, getController());

    public MealyTableUI(Controller controller) {
        super(controller);
        createStateTable(model, COLUMN_LABELS);
        this.setTableWidth(TABLE_WIDTH);
    }

    @Override
    public int getNumCols() {
        return NUM_COLS;
    }

    @Override
    public void setOutputCellEditor() {
        TableColumn outputCol1 = getJTable().getColumnModel().getColumn(3);
        TableColumn outputCol2 = getJTable().getColumnModel().getColumn(4);
        outputCol1.setCellEditor(new OutputCellEditor());
        outputCol1.setCellRenderer(new OutputCellRenderer());
        outputCol2.setCellEditor(new OutputCellEditor());
        outputCol2.setCellRenderer(new OutputCellRenderer());
    }
    
}