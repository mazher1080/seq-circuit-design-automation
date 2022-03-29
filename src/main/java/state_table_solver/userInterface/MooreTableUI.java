package state_table_solver.userInterface;

import javax.swing.table.TableColumn;

import state_table_solver.Controller;
import state_table_solver.userInterface.tableModel.OutputCellEditor;
import state_table_solver.userInterface.tableModel.OutputCellRenderer;
import state_table_solver.userInterface.tableModel.StateTableModel;

/**
 * Used to create the User Interface for a Moore state machine project
 * 
 * @author Muneeb Azher
 * @author Jacob Head
 */
public class MooreTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"Current State", "Next State x = 0", "Next State x = 1", "Output"};
    private static final int NUM_COLS = 4;
    private static final int TABLE_WIDTH = 500;
    private StateTableModel model = new StateTableModel(COLUMN_LABELS, NUM_COLS, getController());

    public MooreTableUI(Controller controller) {
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
        TableColumn outputCol = getJTable().getColumnModel().getColumn(3);
        outputCol.setCellEditor(new OutputCellEditor());
        outputCol.setCellRenderer(new OutputCellRenderer());
    }
}
