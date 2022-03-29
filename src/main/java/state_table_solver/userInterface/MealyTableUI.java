package state_table_solver.userInterface;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import state_table_solver.AppData;

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
    private MealyTableModel model = new MealyTableModel(COLUMN_LABELS, NUM_COLS, getAppData());

    public MealyTableUI(AppData appData) {
        super(appData);
        renderStateTable(model, COLUMN_LABELS, NUM_COLS);
        this.setTableWidth(TABLE_WIDTH);
    }

    @Override
    public int getNumCols() {
        return NUM_COLS;
    }

    @Override
    public void setOutputCellEditor() {
        JTextField textField = new JTextField();
        TableColumn outputCol1 = getjTable().getColumnModel().getColumn(2);
        TableColumn outputCol2 = getjTable().getColumnModel().getColumn(4);
        outputCol1.setCellEditor(new DefaultCellEditor(textField));
        outputCol1.setCellRenderer(new DefaultTableCellRenderer());
        outputCol2.setCellEditor(new DefaultCellEditor(textField));
        outputCol2.setCellRenderer(new DefaultTableCellRenderer());
    }
    
}