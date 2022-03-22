package state_table_solver.userInterface;
/**
 * Used to create the User Interface for a Moore state machine project
 * @author Muneeb Azher
 */
public class MooreTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"Current State", "Next State x = 0", "Next State x = 1", "Output"};
    private static final Object[][] DEFAULT_TABLE = {{null, null, null, null}, {null, null, null, null}};
    private static final int numCols = 4;
    private static final int TABLE_WIDTH = 500;

    public MooreTableUI() {
        super(DEFAULT_TABLE, COLUMN_LABELS, numCols);
        this.setTableWidth(TABLE_WIDTH);
    }

    @Override
    public int getNumCols() {
        return numCols;
        
    }
}
