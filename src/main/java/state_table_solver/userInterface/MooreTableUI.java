package state_table_solver.userInterface;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import state_table_solver.AppData;

/**
 * Used to create the User Interface for a Moore state machine project
 * @author Muneeb Azher
 * @author Jacob Head
 */
public class MooreTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"Current State", "Next State x = 0", "Next State x = 1", "Output"};
    private static final int NUM_COLS = 4;
    private static final int TABLE_WIDTH = 500;
    private MooreTableModel model = new MooreTableModel(COLUMN_LABELS, NUM_COLS, getAppData());

    public MooreTableUI(AppData appData) {
        super(appData);
        renderStateTable(model, COLUMN_LABELS, NUM_COLS);
        this.setTableWidth(TABLE_WIDTH);
    }

    @Override
    public int getNumCols() {
        return NUM_COLS;
        
    }

    public MooreTableModel getModel() {
        return model;
    }

    @Override
    public void setOutputCellEditor() {
        JTextField textField = new JTextField();
        TableColumn outputCol = getjTable().getColumnModel().getColumn(3);
        outputCol.setCellEditor(new DefaultCellEditor(textField));
        outputCol.setCellRenderer(new DefaultTableCellRenderer());
    }
}
