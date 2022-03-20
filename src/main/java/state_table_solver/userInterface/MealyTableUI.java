package state_table_solver.userInterface;

public class MealyTableUI extends StateTableUI {
    private static final String[] COLUMN_LABELS = {"Current State", "Next state x = 0", "Output x = 0", "Next state x = 1", "Output x = 1"};
    private static final Object[][] DEFAULT_TABLE = {{null, null, null, null, null}, {null, null, null, null, null}};
    private int numCols = 5;
    private static final int TABLE_WIDTH = 700;

    public MealyTableUI() {
        super(DEFAULT_TABLE, COLUMN_LABELS);
        this.setTableWidth(TABLE_WIDTH);
    }

    @Override
    public int getNumCols() {
        return this.numCols;
    }
    
}